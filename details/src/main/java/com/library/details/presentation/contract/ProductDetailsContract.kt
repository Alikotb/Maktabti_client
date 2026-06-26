package com.library.details.presentation.contract

enum class ProductAvailability {
    AVAILABLE,
    OUT_OF_STOCK,
    UNKNOWN
}

interface ProductDetailsContract {
    data class ProductDetailsState(
        val isLoading: Boolean = false,
        val product: ProductDetails? = null,
        val error: String? = null,
        val isEmpty: Boolean = false
    )



    sealed interface Intent {
        data class LoadProduct(val productId: String) : Intent
        object ToggleFavorite : Intent
        object ShareProduct : Intent
        object Retry : Intent
        object BackClicked : Intent
    }

    sealed interface Effect {
        object NavigateBack : Effect
    }
}

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