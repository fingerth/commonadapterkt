package com.fingerth.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.fingerth.demo.ui.pager.PagerDemoActivity
import com.fingerth.demo.ui.recycler.RecyclerDemoActivity
import com.fingerth.demo.ui.recycler.RecyclerDemoActivity2
import com.fingerth.demo.utils.ktStartActivity
import com.fingerth.ktadapter.baseadapter.CommonAdapter
import com.fingerth.ktadapter.baseadapter.Holder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    val l = mutableListOf(
        "ViewPager Demo",
        "Unlimited ViewPager Demo(無限滑動)",
        "RecyclerView Demo",
        "RecyclerView Demo(有頭部和foot)",
        "RecyclerView Demo(不同type的item)"
    )
    val list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repeat(10) {
            list.addAll(l)
        }
        lv.adapter =
            object : CommonAdapter<String>(this, list, android.R.layout.simple_list_item_1) {
                override fun convert(holder: Holder, position: Int, data: String) {
                    holder.setText(android.R.id.text1, data)
                }
            }
        lv.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position % l.size) {
            0 -> {
                ktStartActivity<PagerDemoActivity>(this)
            }
            1 -> {
                ktStartActivity<PagerDemoActivity>(this){
                    putExtra("code",1)
                }
            }
            2 -> {
                ktStartActivity<RecyclerDemoActivity>(this)
            }
            3 -> {
                ktStartActivity<RecyclerDemoActivity>(this){
                    putExtra("code",1)
                }
            }
            4 -> {
                ktStartActivity<RecyclerDemoActivity2>(this)
            }
        }
    }
}