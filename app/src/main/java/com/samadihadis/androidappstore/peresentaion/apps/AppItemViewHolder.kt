package com.samadihadis.androidappstore.peresentaion.apps

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.androidappstore.R

class AppItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val descriptionTextView: MaterialTextView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemOneAppLayout)
            iconImageView = findViewById(R.id.itemOneAppIconImageView)
            titleTextView = findViewById(R.id.itemOneAppTitleTextView)
            descriptionTextView = findViewById(R.id.itemOneAppDescriptionTextView)
        }
    }
}