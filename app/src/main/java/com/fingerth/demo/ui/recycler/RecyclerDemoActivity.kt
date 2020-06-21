package com.fingerth.demo.ui.recycler

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fingerth.demo.R
import com.fingerth.demo.utils.showToast
import com.fingerth.ktadapter.recycleradapter.CommonRecyclerAdapter
import com.fingerth.ktadapter.recycleradapter.Holder
import kotlinx.android.synthetic.main.activity_recycler_demo.*

class RecyclerDemoActivity : AppCompatActivity() {
    private val act: Activity = this
    val list: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_demo)
        val code = intent.getIntExtra("code", 0)
        repeat(10) {
            for (i in 1..10) {
                list.add("item = $i")
            }
        }
        val adapter = object : CommonRecyclerAdapter<String>(this, list) {
            override fun setLayoutId(viewType: Int): Int {
                return android.R.layout.simple_list_item_1
            }

            override fun onBind(holder: Holder, RealPosition: Int, data: String) {
                holder.setText(android.R.id.text1, data)
            }
        }
        rv.layoutManager = GridLayoutManager(this, 2)
        when (code) {
            1 -> {
                val mHeaderView = View.inflate(this, R.layout.view_header, null)
                adapter.setHeaderView(mHeaderView)
                val mFootView = View.inflate(this, R.layout.view_foot, null)
                adapter.setFootView(mFootView)
            }
        }

        rv.adapter = adapter
        adapter.setOnItemClickListener(object :
            CommonRecyclerAdapter.OnItemClickListener<String> {
            override fun onItemClick(position: Int, data: String) {
                data.showToast(act)
            }
        })
    }
}