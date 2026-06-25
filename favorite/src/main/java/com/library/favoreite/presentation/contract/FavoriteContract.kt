package com.library.favoreite.presentation.contract

interface FavoriteContract {
    sealed class Intent {
        data class NavigateToDetails(val productId: String) : Intent()
    }

    sealed interface Effect {
        data class NavigateToDetails(val productId: String) : Effect
    }
}
