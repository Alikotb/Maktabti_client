package com.library.favoreite.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.favoreite.presentation.contract.FavoriteContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : ViewModel() {
    private val _effect = MutableSharedFlow<FavoriteContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: FavoriteContract.Intent) {
        when (intent) {
            is FavoriteContract.Intent.NavigateToDetails -> {
                emitEffect(FavoriteContract.Effect.NavigateToDetails(intent.productId))
            }
        }
    }

    private fun emitEffect(effect: FavoriteContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
