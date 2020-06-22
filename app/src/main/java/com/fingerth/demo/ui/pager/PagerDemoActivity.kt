package com.fingerth.demo.ui.pager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fingerth.demo.R
import com.fingerth.ktadapter.pageradapter.CommonPagerAdapter
import com.fingerth.ktadapter.pageradapter.Holder
import com.fingerth.ktadapter.pageradapter.UnlimitedSlidePagerAdapter
import kotlinx.android.synthetic.main.activity_pager_demo.*

class PagerDemoActivity : AppCompatActivity() {
    val list: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager_demo)
        val code = intent.getIntExtra("code", 0)
        for (i in 1..6) {
            list.add("page = $i")
        }
        when (code) {
            0 -> {
                vp.adapter = object : CommonPagerAdapter<String>(this, list, R.layout.pager_item_view) {
                    override fun convert(holder: Holder, position: Int, data: String) {
                        holder.setText(R.id.tv, data)
                        holder.setText(R.id.tv2, position.toString())
                    }
                }
            }
            1 -> {
                val ad =  object : UnlimitedSlidePagerAdapter<String>(this, list, R.layout.pager_item_view) {
                    override fun convert(holder: Holder, position: Int, data: String) {
                        holder.setText(R.id.tv, data)
                        holder.setText(R.id.tv2, position.toString())
                    }
                }
                vp.adapter =ad
                vp.setCurrentItem(ad.getMidPosition(),false)

            }
        }

    }
}