package br.com.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageUrl(url: String?) {
    if (url.isNullOrEmpty()) {
        setImageBitmap(null) //todo set blank image
        return
    }

    Glide.with(context)
        .load(Uri.parse(url))
        .into(this)
}