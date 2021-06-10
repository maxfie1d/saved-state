package com.github.maxfie1d.savedstate

import androidx.lifecycle.SavedStateHandle

abstract class SavedState(
    private val savedStateHandle: SavedStateHandle
) {
    fun <T> savedState(key: String, default: T): SavedStateDelegate<T> {
        return SavedStateDelegate(savedStateHandle, key, default)
    }

    fun <T> savedState(default: T): SavedStateDelegate<T> {
        return SavedStateDelegate(savedStateHandle, default)
    }
}
