package com.samadihadis.androidappstore.peresentaion.adapters.screenshots

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.samadihadis.androidappstore.R

class ScreenshotsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rootLayout: ConstraintLayout
    val iconImageView: AppCompatImageView

    init {
        view.apply {
            rootLayout = findViewById(R.id.rootItemMediumDetailStyleLayout)
            iconImageView = findViewById(R.id.mediumDetailStyleImageView)
        }
    }
}