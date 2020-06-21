package com.fingerth.ktadapter.pageradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.fingerth.ktadapter.utils.BaseHolder

class Holder(val view: View) : BaseHolder {

    override fun <T : View> getView(viewId: Int): T = view.findViewById(viewId)

    override fun setText(viewId: Int, text: String) =
        getView<TextView>(viewId).setText(text)


    override fun setImageResource(viewId: Int, drawableId: Int) =
        getView<ImageView>(viewId).setImageResource(drawableId)

}