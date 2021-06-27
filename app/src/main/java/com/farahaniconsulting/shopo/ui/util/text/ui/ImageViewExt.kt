package com.farahaniconsulting.shopo.ui.util.text.ui

import android.widget.ImageView
import com.farahaniconsulting.shopo.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.load(imageUrl: String) {
    if (imageUrl.isNullOrEmpty() || imageUrl.isBlank()) {
        this.setImageDrawable(this.context.getDrawable(R.drawable.ic_cart_shopping_icon)!!)
    } else {
        Picasso.get()
            .load(imageUrl)
            .error(this.context.getDrawable(R.drawable.ic_cart_shopping_icon)!!)
            .into(this)
    }
}

fun ImageView.load(imageUrl: String?, callback: ImageLoadingCallback) {
    Picasso.get()
        .load(imageUrl)
        .into(this, object : Callback {
            override fun onSuccess() = callback.onLoadingSuccess()

            override fun onError(e: Exception?) = callback.onError(e)
        })
}

interface ImageLoadingCallback {
    fun onLoadingSuccess()
    fun onError(e: Exception?)
}