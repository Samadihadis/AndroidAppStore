package com.samadihadis.androidappstore.peresentaion.adapters.bannerStyle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.util.formatNumberFloat

class AppBannerStyleAdapter : RecyclerView.Adapter<AppBannerStyleViewHolder>() {

    private var onInstallClicked: ((String) -> Unit)? = null
    private var appList: MutableList<AppInfoModel> = mutableListOf()
    private var appItemClickListener : ((AppInfoModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppBannerStyleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_app_banner_style, parent, false)
        return AppBannerStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }

    override fun onBindViewHolder(holder: AppBannerStyleViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            descriptionTextView.text = appList[position].shortDesc
            val rating = appList[position].rating
            ratingTextView.text = rating?.formatNumberFloat() + " " + "★"
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
                appItemClickListener?.invoke(appList[position])
            }
            installButton.setOnClickListener{
                onInstallClicked?.invoke(appList[position].packageName ?: "")
            }
        }
    }

    fun onInstallButtonClickListener(listener: ((String) -> Unit)) {
        onInstallClicked = listener
    }
    fun onItemBannerStyleClickListener(listener: ((AppInfoModel) -> Unit)) {
        appItemClickListener = listener
    }
}