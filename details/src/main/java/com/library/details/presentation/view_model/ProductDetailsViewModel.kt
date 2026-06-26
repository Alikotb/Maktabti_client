package com.library.details.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.core.R
import com.library.details.presentation.contract.ProductAvailability
import com.library.details.presentation.contract.ProductDetails
import com.library.details.presentation.contract.ProductDetailsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsContract.ProductDetailsState())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<ProductDetailsContract.Effect>()
    val effect = _effect.asSharedFlow()


    private fun emitEffect(effect: ProductDetailsContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    fun onIntent(intent: ProductDetailsContract.Intent) {
        when (intent) {
            is ProductDetailsContract.Intent.LoadProduct -> loadProduct(intent.productId)
            ProductDetailsContract.Intent.ToggleFavorite -> toggleFavorite()
            ProductDetailsContract.Intent.ShareProduct -> shareProduct()
            ProductDetailsContract.Intent.Retry -> {
                _uiState.value.product?.id?.let { loadProduct(it) }
            }

            ProductDetailsContract.Intent.BackClicked -> {
                emitEffect(ProductDetailsContract.Effect.NavigateBack)
            }
        }
    }

    private fun loadProduct(productId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, isEmpty = false) }

            // Simulating API call
            delay(500)

            // Mock Data
            val mockProduct = ProductDetails(
                id = productId,
                name = "دفتر ملاحظات جلدي فاخر",
                price = 120.0,
                image = R.drawable.p_books,
                description = "دفتر ملاحظات عالي الجودة بغلاف جلدي طبيعي. يحتوي على 200 صفحة من الورق الفاخر المناسب لجميع أنواع الأقلام. مثالي للمهنيين والطلاب وعشاق التدوين.",
                category = "أدوات مكتبية",
                isNew = true,
                isSpecial = true,
                isFavorite = false,
                availability = ProductAvailability.OUT_OF_STOCK,
                offer = "خصم 15% لفترة محدودة"
            )

            _uiState.update {
                it.copy(
                    isLoading = false,
                    product = mockProduct,
                    isEmpty = false
                )
            }
        }
    }

    private fun toggleFavorite() {
        _uiState.update { state ->
            state.copy(
                product = state.product?.copy(isFavorite = !state.product.isFavorite)
            )
        }
    }

    private fun shareProduct() {
        // Implementation for sharing
    }
}
