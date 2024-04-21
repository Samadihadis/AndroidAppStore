package com.samadihadis.androidappstore.peresentaion.apps.sport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.peresentaion.apps.business.BusinessItemViewHolder
import com.samadihadis.androidappstore.util.formatNumber

class SportListAdapter : RecyclerView.Adapter<SportItemViewHolder>() {

    private var appList: MutableList<AppInfoModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_app_three, parent, false)
        return SportItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: SportItemViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            descriptionTextView.text = appList[position].shortDesc
            val rating = appList[position].rating
            ratingTextView.text = rating.formatNumber() + " " + "★"
            Glide.with(rootLayout.context)
                .load(appList[position].featuredGraphic)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(bannerImageView)
            Glide.with(rootLayout.context)
                .load(appList[position].icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
            rootLayout.setOnClickListener {

            }
        }
    }
}