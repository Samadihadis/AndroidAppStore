package com.samadihadis.androidappstore.peresentaion.apps.sport

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class SportItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val rootLayout: ConstraintLayout
    val bannerImageView: AppCompatImageView
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val descriptionTextView : MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemThreeAppLayout)
            bannerImageView = findViewById(R.id.itemThreeAppBannerImageView)
            iconImageView = findViewById(R.id.itemThreeAppIconImageView)
            titleTextView = findViewById(R.id.itemThreeAppTitleTextView)
            descriptionTextView = findViewById(R.id.itemThreeAppDescriptionTextView)
            ratingTextView = findViewById(R.id.itemThreeAppRatingTextView)
        }
    }
}