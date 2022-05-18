package com.jmanrique.loldataproject.utils.extensions

infix fun <T> Collection<T>.sameContentWith(collection: Collection<T>?)
        = collection?.let { this.size == it.size && this.containsAll(it) } ?: run { false }