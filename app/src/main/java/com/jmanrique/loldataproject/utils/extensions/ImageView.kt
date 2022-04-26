package com.jmanrique.loldataproject.utils.extensions

import android.graphics.PorterDuff
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String, placeholder: Int? = null, colorFilter: Boolean? = false) {
    placeholder?.let {
        Glide.with(context).load(url).placeholder(it).into(this)
    } ?: run {
        Glide.with(context).load(url).into(this)
    }
    colorFilter?.let {
        if (colorFilter) {
            this.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
        } else {
            this.clearColorFilter()
        }
    }
}