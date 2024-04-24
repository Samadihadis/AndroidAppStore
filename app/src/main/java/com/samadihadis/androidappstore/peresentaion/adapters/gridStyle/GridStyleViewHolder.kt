package com.samadihadis.androidappstore.peresentaion.adapters.gridStyle

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class GridStyleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val descriptionTextView : MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemBoxStyleLayout)
            iconImageView = findViewById(R.id.boxStyleIconImageView)
            titleTextView = findViewById(R.id.boxStyleTitleTextView)
            descriptionTextView = findViewById(R.id.boxStyleDescriptionTextView)
            ratingTextView = findViewById(R.id.boxStyleRatingTextView)
        }
    }
}