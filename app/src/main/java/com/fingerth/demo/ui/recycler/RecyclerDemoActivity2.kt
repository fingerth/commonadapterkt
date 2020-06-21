package com.fingerth.demo.ui.recycler

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fingerth.demo.R
import com.fingerth.demo.utils.showToast
import com.fingerth.ktadapter.recycleradapter.CommonRecyclerAdapter
import com.fingerth.ktadapter.recycleradapter.Holder
import kotlinx.android.synthetic.main.activity_recycler_demo2.*

class RecyclerDemoActivity2 : AppCompatActivity() {
    private val act: Activity = this
    val list: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_demo2)
        repeat(10) {
            for (i in 1..10) {
                list.add("item = $i")
            }
        }
        val adapter = object : CommonRecyclerAdapter<String>(this, list) {
            override fun itemViewType(position: Int): Int {
                return if (position % 3 == 1) {
                    0x88
                } else super.itemViewType(position)
            }
            override fun setLayoutId(viewType: Int): Int {
                return when (viewType) {
                    0x88 -> R.layout.view_foot
                    else -> android.R.layout.simple_list_item_1
                }
            }

            override fun onBind(holder: Holder, RealPosition: Int, data: String) {
                when (holder.itemViewType) {
                    0x88 -> holder.setText(R.id.tv, data)
                    else -> holder.setText(android.R.id.text1, data)
                }
            }
        }
        rv.layoutManager = GridLayoutManager(this, 2)
//        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        adapter.setOnItemClickListener(object :
            CommonRecyclerAdapter.OnItemClickListener<String> {
            override fun onItemClick(position: Int, data: String) {
                data.showToast(act)
            }
        })
    }
}