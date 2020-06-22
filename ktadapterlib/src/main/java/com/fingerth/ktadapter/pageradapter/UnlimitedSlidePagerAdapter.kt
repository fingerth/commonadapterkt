package com.fingerth.ktadapter.pageradapter

import android.content.Context
import androidx.annotation.LayoutRes

abstract class UnlimitedSlidePagerAdapter<T>(
    override val context: Context,
    override val mDataList: List<T>,
    @LayoutRes mLayoutId: Int
) : CommonPagerAdapter<T>(context, mDataList, mLayoutId) {
    private val PAGER_COUNT = 5000000 //假无限滑动，这个数够用了吧！
    private val PAGER_COUNT_MID = 2500000 //假无限滑动，这个数够用了吧！

    open fun getMidPosition(): Int = PAGER_COUNT_MID - (PAGER_COUNT_MID % mDataList.size)


    override fun getCount(): Int = PAGER_COUNT

}

