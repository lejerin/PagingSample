package com.example.paging3.ui.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.example.paging3.R
import com.example.paging3.base.BaseActivity
import com.example.paging3.data.FestivalItem
import com.example.paging3.databinding.ActivityDetailBinding


class DetailActivity : BaseActivity() {

    private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)
    private val item by lazy { intent.getSerializableExtra(KEY_DATA) as FestivalItem }

    companion object {
        private const val KEY_DATA = "festival"
        fun startActivityWithTransition(
            activity: Activity,
            imageView: ImageView,
            data: FestivalItem
        ) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(KEY_DATA, data)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, imageView, imageView.transitionName
            )
            activity.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeTransition()
        Log.i("eunjin", "data ${item.title}")
        binding.apply {
            lifecycleOwner = this@DetailActivity
            Glide.with(imageView.context)
                .load(item.firstimage)
                .signature(ObjectKey(item.firstimage))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                        startPostponedTransition()
                        return false
                    }
                })
                .into(imageView)
        }
    }

    private fun postponeTransition() {
        postponeEnterTransition()
    }

    private fun startPostponedTransition() {
        startPostponedEnterTransition()
    }
}