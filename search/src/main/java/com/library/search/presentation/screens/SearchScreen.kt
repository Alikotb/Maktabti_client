package com.library.search.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.core.ui.component.MySearchBar
import com.library.core.ui.component.Product
import com.library.core.ui.component.ProductsCard
import com.library.core.ui.component.featuredProducts
import com.library.core.ui.theme.Tajawal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ============================================================================
 * ARCHITECTURAL CONTRACTS (MVI Pattern)
 * ============================================================================
 */

/**
 * Represents the 4 filter conditions requested by the design guidelines.
 */
enum class SearchFilter(val labelAr: String) {
    PriceLowToHigh("السعر: من الأقل للأعلى"),
    PriceHighToLow("السعر: من الأعلى للأقل"),
    New("جديد"),
    Special("مميز")
}

/**
 * UI State that encapsulates everything displayed on the Search Screen.
 * Ensures Unidirectional Data Flow (UDF) and remains completely immutable.
 */
data class SearchUiState(
    val searchQuery: String = "",
    val selectedFilter: SearchFilter? = null,
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)

/**
 * MVI Intent/Action definitions handling user interactions decoupled from logic.
 */
sealed interface SearchIntent {
    data class UpdateQuery(val query: String) : SearchIntent
    data class SelectFilter(val filter: SearchFilter?) : SearchIntent
    data class ToggleFavorite(val productId: String) : SearchIntent
    object ClearSearch : SearchIntent
    object Retry : SearchIntent
}

/**
 * ============================================================================
 * VIEWMODEL IMPLEMENTATION
 * ============================================================================
 */
@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null
    private var mockProductList = featuredProducts.toMutableList()

    init {
        executeSearch(query = "", filter = null)
    }

    fun onIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.UpdateQuery -> {
                _uiState.update { it.copy(searchQuery = intent.query) }
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(300)
                    executeSearch(_uiState.value.searchQuery, _uiState.value.selectedFilter)
                }
            }

            is SearchIntent.SelectFilter -> {
                val newFilter =
                    if (_uiState.value.selectedFilter == intent.filter) null else intent.filter
                _uiState.update { it.copy(selectedFilter = newFilter) }
                executeSearch(_uiState.value.searchQuery, newFilter)
            }

            is SearchIntent.ToggleFavorite -> {
                mockProductList = mockProductList.map {
                    if (it.id == intent.productId) it.copy(isFavorite = !it.isFavorite) else it
                }.toMutableList()
                executeSearch(_uiState.value.searchQuery, _uiState.value.selectedFilter)
            }

            is SearchIntent.ClearSearch -> {
                _uiState.update { it.copy(searchQuery = "", selectedFilter = null) }
                executeSearch("", null)
            }

            is SearchIntent.Retry -> {
                _uiState.update { it.copy(errorMessage = null) }
                executeSearch(_uiState.value.searchQuery, _uiState.value.selectedFilter)
            }
        }
    }

    private fun executeSearch(query: String, filter: SearchFilter?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(200)
            try {
                if (query.trim().equals("error", ignoreCase = true)) {
                    throw RuntimeException("حدث خطأ أثناء الاتصال بالخادم. يرجى المحاولة مرة أخرى.")
                }
                var filtered = mockProductList.filter {
                    it.name.contains(query, ignoreCase = true)
                }
                filtered = when (filter) {
                    SearchFilter.PriceLowToHigh -> filtered.sortedBy { it.price }
                    SearchFilter.PriceHighToLow -> filtered.sortedByDescending { it.price }
                    SearchFilter.New -> filtered.sortedByDescending { it.isNew }
                    SearchFilter.Special -> filtered.filter { it.isFavorite || it.isNew }
                    null -> filtered
                }
                _uiState.update { it.copy(isLoading = false, products = filtered) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = emptyList(),
                        errorMessage = e.message
                    )
                }
            }
        }
    }
}

/**
 * ============================================================================
 * COMPOSE UI COMPONENTS
 * ============================================================================
 */

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SearchContent(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onIntent = viewModel::onIntent
    )
}

@Composable
fun SearchContent(
    uiState: SearchUiState,
    onIntent: (SearchIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val columnCount = if (configuration.screenWidthDp >= 600) 3 else 2

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MySearchBar(
                query = uiState.searchQuery,
                onQueryChange = { onIntent(SearchIntent.UpdateQuery(it)) },
                autoFocus = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            )

            SearchFilterSection(
                selectedFilter = uiState.selectedFilter,
                onFilterSelected = { onIntent(SearchIntent.SelectFilter(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                when {
                    uiState.isLoading && uiState.products.isEmpty() -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.Center),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    uiState.errorMessage != null -> {
                        ErrorSearchState(
                            message = uiState.errorMessage ,
                            onRetry = { onIntent(SearchIntent.Retry) },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    uiState.products.isEmpty() -> {
                        EmptySearchState(
                            query = uiState.searchQuery,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    else -> {
                        SearchResultsGrid(
                            products = uiState.products,
                            columns = columnCount,
                            onProductClick = { /* Navigate to Product details */ },
                            onFavoriteClick = { onIntent(SearchIntent.ToggleFavorite(it.id)) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                SearchLoadingOverlay(
                    visible = uiState.isLoading && uiState.products.isNotEmpty(),
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}

/**
 * Separate component for loading overlay to avoid ColumnScope/BoxScope ambiguity with AnimatedVisibility.
 */
@Composable
private fun SearchLoadingOverlay(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier.fillMaxWidth()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SearchFilterSection(
    selectedFilter: SearchFilter?,
    onFilterSelected: (SearchFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(SearchFilter.entries.toTypedArray()) { filter ->
            val isSelected = filter == selectedFilter
            Surface(
                modifier = Modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onFilterSelected(filter) },
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.85f) else MaterialTheme.colorScheme.surface/*.copy(alpha = 0.5f)*/,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if (!isSelected) MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface/*.copy(alpha = 0.2f)*/
                )
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = filter.labelAr,
                        fontFamily = Tajawal,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun SearchResultsGrid(
    products: List<Product>,
    columns: Int,
    onProductClick: (Product) -> Unit,
    onFavoriteClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(
            items = products,
            key = { it.id + "_" + it.isFavorite + "_" + it.isNew }
        ) { product ->
            ProductsCard(
                product = product,
                onProductClick = onProductClick,
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun EmptySearchState(
    query: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "لا توجد نتائج بحث",
            fontFamily = Tajawal,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (query.isNotEmpty()) "لم نجد أي منتج يطابق \"$query\"، جرب كلمات أخرى." else "ابدأ بالبحث عن منتجاتك المفضلة الآن.",
            fontFamily = Tajawal,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ErrorSearchState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            fontFamily = Tajawal,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "إعادة المحاولة", fontFamily = Tajawal, fontWeight = FontWeight.Bold)
        }
    }
}
