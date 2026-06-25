package com.library.home.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.home.presentation.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect = _effect.asSharedFlow()


    fun onIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.NavigateToSearch -> {
                emitEffect(HomeContract.Effect.NavigateToSearch)
            }
            is HomeContract.Intent.NavigateToDetails -> {
                emitEffect(HomeContract.Effect.NavigateToDetails(intent.productId))
            }
        }
    }

    private fun emitEffect(effect: HomeContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

}