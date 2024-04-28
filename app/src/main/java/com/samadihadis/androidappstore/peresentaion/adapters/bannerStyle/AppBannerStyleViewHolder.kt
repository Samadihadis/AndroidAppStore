package com.samadihadis.androidappstore.peresentaion.adapters.bannerStyle

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class AppBannerStyleViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val rootLayout: ConstraintLayout
    val bannerImageView: AppCompatImageView
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val descriptionTextView : MaterialTextView
    val ratingTextView: MaterialTextView
    val installButton: MaterialButton

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemBannerStyleLayout)
            bannerImageView = findViewById(R.id.bannerStyleImageView)
            iconImageView = findViewById(R.id.bannerStyleIconImageView)
            titleTextView = findViewById(R.id.bannerStyleTitleTextView)
            descriptionTextView = findViewById(R.id.bannerStyleDescriptionTextView)
            ratingTextView = findViewById(R.id.bannerStyleRatingTextView)
            installButton = findViewById(R.id.bannerStyleInstallButton)
        }
    }
}