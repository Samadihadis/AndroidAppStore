package com.samadihadis.androidappstore.peresentaion.apps.application

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class ApplicationItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemOneAppLayout)
            iconImageView = findViewById(R.id.itemOneAppIconImageView)
            titleTextView = findViewById(R.id.itemOneAppTitleTextView)
            ratingTextView = findViewById(R.id.itemOneAppRatingTextView)
        }
    }
}