package com.jmanrique.loldataproject.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String, placeholder: Int? = null) {
    placeholder?.let {
        Glide.with(context).load(url).placeholder(it).into(this)
    } ?: run {
        Glide.with(context).load(url).into(this)
    }
}