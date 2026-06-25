package com.library.sections.presentation.contract


interface CategoriesContract {
    sealed class Intent {
        object NavigateToSearch : Intent()
        object NavigateBack : Intent()
        data class NavigateToDetails(val productId: String) : Intent()
    }

    sealed interface Effect {
        object NavigateToSearch : Effect

        object NavigateBack : Effect
        data class NavigateToDetails(val productId: String) : Effect
    }
}