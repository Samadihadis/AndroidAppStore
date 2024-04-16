package com.samadihadis.androidappstore.peresentaion.apps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel

class AppsListAdapter : RecyclerView.Adapter<AppItemViewHolder>() {

    private var appList : MutableList<AppInfoModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_apps_one, parent, false)
        return AppItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: AppItemViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            descriptionTextView.text = appList[position].rating.toString() + " ★"
            Glide.with(rootLayout.context)
                .load(appList[position].icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
        }
    }
}