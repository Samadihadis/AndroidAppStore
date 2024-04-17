package com.samadihadis.androidappstore.util

import java.text.DecimalFormat

fun Float.formatNumber(): String {
    val num = DecimalFormat("##.#")
    return num.format(this)
}