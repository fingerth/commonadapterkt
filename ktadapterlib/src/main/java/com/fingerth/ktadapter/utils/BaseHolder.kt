package com.fingerth.ktadapter.utils

import android.view.View
import androidx.annotation.IdRes

interface BaseHolder {

    abstract fun <T : View> getView(@IdRes viewId: Int): T
    abstract fun setText(@IdRes viewId: Int, text: String)
    abstract fun setImageResource(@IdRes viewId: Int, drawableId: Int)



}