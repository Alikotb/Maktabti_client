package com.library.home.presentation.contract


interface HomeContract {
    sealed class Intent {
        object NavigateToSearch : Intent()

    }

    sealed interface Effect {
        object NavigateToSearch : Effect
    }
}