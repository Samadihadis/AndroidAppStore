package com.samadihadis.androidappstore.peresentaion.adapters.gridStyle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.util.formatNumber


class AppGridStyleAdapter : RecyclerView.Adapter<AppGridStyleViewHolder>() {

    private var appList: MutableList<AppInfoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppGridStyleViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_app_grid_style, parent, false)
        return AppGridStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: AppGridStyleViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            val rating = appList[position].rating
            ratingTextView.text = rating.formatNumber() + " " + "★"
            Glide.with(rootLayout.context)
                .load(appList[position].icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
        }
    }
}