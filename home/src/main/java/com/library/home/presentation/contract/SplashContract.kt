package com.library.home.presentation.contract


interface SplashContract {
    sealed class Intent {
        object NavigateToNext : Intent()

    }

    sealed interface Effect {
        object NavigateToHome : Effect
    }
}