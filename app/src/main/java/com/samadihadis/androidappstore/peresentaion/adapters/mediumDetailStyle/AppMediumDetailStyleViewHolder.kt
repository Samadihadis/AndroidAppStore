package com.samadihadis.androidappstore.peresentaion.adapters.mediumDetailStyle

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class AppMediumDetailStyleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val downloadsTextView: MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemMediumDetailStyleLayout)
            iconImageView = findViewById(R.id.mediumDetailStyleImageView)
            titleTextView = findViewById(R.id.mediumDetailStyleTitleTextView)
            downloadsTextView = findViewById(R.id.detailPageDownloadTitleTextView)
            ratingTextView = findViewById(R.id.mediumDetailStyleRatingTextView)
        }
    }
}