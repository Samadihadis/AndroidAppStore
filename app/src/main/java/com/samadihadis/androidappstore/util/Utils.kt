package com.samadihadis.androidappstore.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object Utils {

    fun openMarket(context: Context , packageName : String) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                )
            )
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }

    fun Double?.orZero(): Double = this ?: 0.0

    fun Float?.orZero(): Float = this ?: 0f

    fun String?.orEmpty(): String = this ?: ""

}