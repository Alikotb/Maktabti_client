package com.library.details.presentation.contract

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val product: ProductDetails? = null,
    val error: String? = null,
    val isEmpty: Boolean = false
)

data class ProductDetails(
    val id: String,
    val name: String,
    val price: Double,
    val currency: String = "ج.م",
    val image: Int, // Using Int for resources as seen in the core module
    val description: String,
    val category: String?,
    val isNew: Boolean = false,
    val isSpecial: Boolean = false,
    val isFavorite: Boolean = false,
    val availability: ProductAvailability = ProductAvailability.UNKNOWN,
    val offer: String? = null
)

enum class ProductAvailability {
    AVAILABLE,
    OUT_OF_STOCK,
    UNKNOWN
}

sealed interface ProductDetailsIntent {
    data class LoadProduct(val productId: String) : ProductDetailsIntent
    object ToggleFavorite : ProductDetailsIntent
    object ShareProduct : ProductDetailsIntent
    object Retry : ProductDetailsIntent
    object BackClicked : ProductDetailsIntent
}
