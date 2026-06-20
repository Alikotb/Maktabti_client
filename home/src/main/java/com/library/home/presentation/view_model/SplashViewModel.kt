package com.library.home.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.home.presentation.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel()  {
    private val _effect = MutableSharedFlow<SplashContract.Effect>()
    val effect = _effect.asSharedFlow()


    fun onIntent(intent: SplashContract.Intent) {
        when (intent) {
            SplashContract.Intent.NavigateToNext -> {
                emitEffect(SplashContract.Effect.NavigateToHome)
            }

        }
    }
    private fun emitEffect(effect: SplashContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

}