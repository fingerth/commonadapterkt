package com.fingerth.ktadapter.pageradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.PagerAdapter

abstract class CommonPagerAdapter<T>(
    open val context: Context,
    open val mDataList: List<T>,
    @LayoutRes val mLayoutId: Int
) : PagerAdapter() {

    override fun getCount(): Int = mDataList.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view === obj


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pos: Int = position % mDataList.size
        //val pagerHolder = Holder(View.inflate(context, mLayoutId, null))
        val pagerHolder = Holder(LayoutInflater.from(context).inflate(mLayoutId, null, false))
        convert(pagerHolder, pos, mDataList[pos])
        container.addView(pagerHolder.view)
        return pagerHolder.view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) =
        container.removeView(obj as View)


    abstract fun convert(holder: Holder, position: Int, data: T)
}