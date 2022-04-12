package com.jmanrique.loldataproject.utils.extensions

fun String?.safeValue(default: String = "") =
    this?.let {
        return@let it
    } ?: default
