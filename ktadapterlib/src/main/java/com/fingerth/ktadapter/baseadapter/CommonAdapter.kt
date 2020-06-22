package com.fingerth.ktadapter.baseadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

abstract class CommonAdapter<T>(
     val context: Context,
     var mDataList: List<T>,
    private val itemLayoutId: Int
) : BaseAdapter() {

    override fun getCount(): Int = mDataList.size


    override fun getItem(position: Int): Any = mDataList[position]!!

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder = Holder.getHolder(context, convertView, parent, itemLayoutId, position)
        convert(holder, position, mDataList[position])
        return holder.getConvertView()
    }

    abstract fun convert(holder: Holder, position: Int, data: T)


}