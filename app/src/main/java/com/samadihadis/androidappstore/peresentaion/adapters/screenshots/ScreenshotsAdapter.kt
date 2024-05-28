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

    private var appList: MutableList<AppInfoModel> = mutableListOf()

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
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        holder.apply {
            Glide.with(rootLayout.context)
                .load(appList[position].screenshots)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)        }
    }
}