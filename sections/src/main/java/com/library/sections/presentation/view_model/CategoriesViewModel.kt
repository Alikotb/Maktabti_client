package com.library.sections.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.sections.presentation.contract.CategoriesContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() : ViewModel() {
    private val _effect = MutableSharedFlow<CategoriesContract.Effect>()
    val effect = _effect.asSharedFlow()


    fun onIntent(intent: CategoriesContract.Intent) {
        when (intent) {
            CategoriesContract.Intent.NavigateToSearch -> {
                emitEffect(CategoriesContract.Effect.NavigateToSearch)
            }

            is CategoriesContract.Intent.NavigateToDetails -> {
                emitEffect(CategoriesContract.Effect.NavigateToDetails(intent.productId))
            }
            CategoriesContract.Intent.NavigateBack -> {
                emitEffect(CategoriesContract.Effect.NavigateBack)
            }
        }
    }

    private fun emitEffect(effect: CategoriesContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

}