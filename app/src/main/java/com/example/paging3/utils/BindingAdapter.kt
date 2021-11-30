package com.example.paging3.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import java.io.File
import java.lang.Exception

object BindingAdapter {

    @androidx.databinding.BindingAdapter("bind:glideCacheImage")
    @JvmStatic
    fun setGlideImage(imageView: ImageView, url: String?) {
        try{
            url?.let {
                Glide.with(imageView.context)
                    .load(it)
                    .signature(ObjectKey(it))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}