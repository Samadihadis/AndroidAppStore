package com.samadihadis.androidappstore.peresentaion.adapters.smallDetailStyle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.util.formatNumber


class AppSmallDetailStyleAdapter : RecyclerView.Adapter<AppSmallDetailStyleViewHolder>() {

    private var appList: MutableList<AppInfoModel> = mutableListOf()
    private var appItemClickListener : ((AppInfoModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppSmallDetailStyleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_app_small_detail_style, parent, false)
        return AppSmallDetailStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: AppSmallDetailStyleViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            val rating = appList[position].rating
            ratingTextView.text = rating.formatNumber() + " " + "★"
            Glide.with(rootLayout.context)
                .load(appList[position].icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
            rootLayout.setOnClickListener {
                appItemClickListener?.invoke(appList[position])
            }
        }
    }
    fun onItemSmallStyleClickListener(listener: ((AppInfoModel) -> Unit)) {
        appItemClickListener = listener
    }
}