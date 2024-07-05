package com.samadihadis.androidappstore.peresentaion.adapters.smallDetailStyle

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class AppSmallDetailStyleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemSmallDetailStyleLayout)
            iconImageView = findViewById(R.id.smallDetailStyleIconImageView)
            titleTextView = findViewById(R.id.smallDetailStyleTitleTextView)
            ratingTextView = findViewById(R.id.smallDetailStyleAppRatingTextView)
        }
    }
}