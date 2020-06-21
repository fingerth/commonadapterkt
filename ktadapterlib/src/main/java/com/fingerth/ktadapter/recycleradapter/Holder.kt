package com.fingerth.ktadapter.recycleradapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fingerth.ktadapter.utils.BaseHolder

class Holder(private val view: View) : RecyclerView.ViewHolder(view), BaseHolder {

    override fun <T : View> getView(viewId: Int): T = view.findViewById(viewId)

    override fun setText(viewId: Int, text: String) =
        getView<TextView>(viewId).setText(text)


    override fun setImageResource(viewId: Int, drawableId: Int) =
        getView<ImageView>(viewId).setImageResource(drawableId)
}