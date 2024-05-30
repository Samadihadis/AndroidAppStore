package com.samadihadis.androidappstore.util

import android.view.View
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale




fun Float.formatNumberFloat(): String {
    val num = DecimalFormat("##.#")
    return num.format(this)
}

fun Double.formatNumberDouble(): String {
    val num = DecimalFormat("##.##")
    return num.format(this)
}
fun Int.separatorNumbers(): String {
    val num = NumberFormat.getInstance(Locale.US) as DecimalFormat
    num.applyPattern("#,###")
    return num.format(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}
