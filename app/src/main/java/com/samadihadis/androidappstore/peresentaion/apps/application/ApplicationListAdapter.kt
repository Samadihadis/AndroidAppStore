package com.samadihadis.androidappstore.peresentaion.apps.application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.util.formatNumber
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToLong

class ApplicationListAdapter : RecyclerView.Adapter<ApplicationItemViewHolder>() {

    private var appList: MutableList<AppInfoModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_apps_one, parent, false)
        return ApplicationItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun addItemList(appModelList: List<AppInfoModel>) {
        appList.addAll(appModelList)
        notifyItemRangeInserted(appList.size - 1, appModelList.size)
    }
    override fun onBindViewHolder(holder: ApplicationItemViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = appList[position].title
            val rating = appList[position].rating
            ratingTextView.text = rating.formatNumber() + "★"
            Glide.with(rootLayout.context)
                .load(appList[position].icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(60))
                .into(iconImageView)
        }
    }
}