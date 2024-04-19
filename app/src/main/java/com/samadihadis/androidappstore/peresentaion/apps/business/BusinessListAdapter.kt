package com.samadihadis.androidappstore.peresentaion.apps.business

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.util.formatNumber


class BusinessListAdapter : RecyclerView.Adapter<BusinessItemViewHolder>() {

    private var appList: MutableList<AppInfoModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_apps_two, parent, false)
        return BusinessItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: BusinessItemViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            val rating = appList[position].rating
            ratingTextView.text = rating.formatNumber() + " " + "★"
            descriptionTextView.text = appList[position].shortDesc
            Glide.with(rootLayout.context)
                .load(appList[position].icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
        }
    }
}