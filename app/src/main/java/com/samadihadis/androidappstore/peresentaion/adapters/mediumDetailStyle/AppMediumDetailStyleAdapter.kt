package com.samadihadis.androidappstore.peresentaion.adapters.mediumDetailStyle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.util.formatNumberFloat


class AppMediumDetailStyleAdapter : RecyclerView.Adapter<AppMediumDetailStyleViewHolder>() {

    private var appList: MutableList<AppInfoModel> = mutableListOf()
    private var appItemClickListener: ((AppInfoModel) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppMediumDetailStyleViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_app_medium_detail_style, parent, false)
        return AppMediumDetailStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: AppMediumDetailStyleViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            val rating = appList[position].rating
            ratingTextView.text = "${rating?.formatNumberFloat()} ★"
            downloadsTextView.text = appList[position].downloads
            Glide.with(rootLayout.context)
                .load(appList[position].featuredGraphic)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
            rootLayout.setOnClickListener {
                appItemClickListener?.invoke(appList[position])
            }
        }
    }
    fun onItemMediumStyleClickListener(listener: ((AppInfoModel) -> Unit)) {
        appItemClickListener = listener
    }
}