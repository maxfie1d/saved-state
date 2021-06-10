package com.github.maxfie1d.savedstate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val savedState = object : SavedState(savedStateHandle) {
        var count by savedState(default = 0)
    }

    private val _count = MutableStateFlow(savedState.count)
    val count = _count.asStateFlow()

    fun increment() = viewModelScope.launch {
        val result = ++savedState.count
        _count.emit(result)
    }.ignore()

    private fun Any.ignore() = Unit
}
