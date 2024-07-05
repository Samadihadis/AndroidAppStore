package com.samadihadis.androidappstore.peresentaion.adapters.gridStyle

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class AppGridStyleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemGridStyleLayout)
            iconImageView = findViewById(R.id.gridStyleIconImageView)
            titleTextView = findViewById(R.id.gridStyleTitleTextView)
            ratingTextView = findViewById(R.id.gridStyleAppRatingTextView)
        }
    }
}