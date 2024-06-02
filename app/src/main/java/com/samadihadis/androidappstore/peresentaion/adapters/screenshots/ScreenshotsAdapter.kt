package com.samadihadis.androidappstore.peresentaion.adapters.screenshots

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel


class ScreenshotsAdapter : RecyclerView.Adapter<ScreenshotsViewHolder>() {

    private var screenshotList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScreenshotsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_screenshot, parent, false)
        return ScreenshotsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return screenshotList.size
    }

    fun addItemList(screenshotUrlList: List<String>) {
        screenshotList.addAll(screenshotUrlList)
        notifyItemRangeInserted(screenshotList.size - 1, screenshotUrlList.size)
    }

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        holder.apply {
            Glide.with(rootLayout.context)
                .load(screenshotList[position])
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(30))
                .into(iconImageView)
        }
    }
}