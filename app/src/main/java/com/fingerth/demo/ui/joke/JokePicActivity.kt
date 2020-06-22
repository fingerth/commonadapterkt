package com.fingerth.demo.ui.joke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.fingerth.demo.R
import com.fingerth.demo.logic.model.JokePicData
import com.fingerth.ktadapter.recycleradapter.CommonRecyclerAdapter
import com.fingerth.ktadapter.recycleradapter.Holder
import kotlinx.android.synthetic.main.activity_joke_pic.*

class JokePicActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(JokeViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_pic)
        rv.layoutManager = GridLayoutManager(this, 2)
        initData()
    }

    private fun initData() {
        val map = mapOf<String, String>("type" to "pic", "page" to "1", "pagesize" to "20")
        viewModel.searchJokes(map)

        viewModel.jokeData.observe(this, Observer {
            val res = it.getOrNull()
            res?.let {
                rv.adapter = object :CommonRecyclerAdapter<JokePicData>(this,res.data){
                    override fun setLayoutId(viewType: Int): Int {
                       return R.layout.item_rv_joke_pic
                    }
                    override fun onBind(holder: Holder, RealPosition: Int, data: JokePicData) {
                        holder.setText(R.id.tv,data.content)
                        val iv = holder.getView<ImageView>(R.id.iv)
                        Glide.with(context).load(data.url).into(iv)
                    }


                }
            }


        })

    }
}