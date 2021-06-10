package com.github.maxfie1d.savedstate

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SavedStateDelegate<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String?,
    private val default: T
) : ReadWriteProperty<Any, T> {
    constructor(
        ssh: SavedStateHandle,
        default: T
    ) : this(ssh, null, default)

    init {
        if (key != null) {
            require(key.isNotEmpty())
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return savedStateHandle.get<T>(computeKey(property)) ?: default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        savedStateHandle.set(computeKey(property), value)
    }

    private fun computeKey(property: KProperty<*>) = key ?: property.name
}

fun <T> savedState(
    savedStateHandle: SavedStateHandle,
    key: String,
    default: T
): ReadWriteProperty<Any, T> {
    return SavedStateDelegate(savedStateHandle, key, default)
}

fun <T> savedState(
    savedStateHandle: SavedStateHandle,
    default: T
): ReadWriteProperty<Any, T> {
    return SavedStateDelegate(savedStateHandle, default)
}
