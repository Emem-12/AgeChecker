package com.example.agechecker

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Category (
    @DrawableRes
    var icon:Int,
    var question: String,
    @ColorRes
    var color: Int )