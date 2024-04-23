package com.samadihadis.androidappstore.util

import android.view.View
import java.text.DecimalFormat

fun Float.formatNumber(): String {
    val num = DecimalFormat("##.#")
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
