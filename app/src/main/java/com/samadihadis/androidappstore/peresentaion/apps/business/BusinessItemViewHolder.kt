package com.samadihadis.androidappstore.peresentaion.apps.business

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class BusinessItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val descriptionTextView : MaterialTextView
    val ratingTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemTwoAppLayout)
            iconImageView = findViewById(R.id.itemTwoAppIconImageView)
            titleTextView = findViewById(R.id.itemTwoAppTitleTextView)
            descriptionTextView = findViewById(R.id.itemTwoAppDescriptionTextView)
            ratingTextView = findViewById(R.id.itemTwoAppRatingTextView)
        }
    }
}