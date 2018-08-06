package com.mirstone.andframe.module

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.mirstone.andframe.R
import com.mirstone.baselib.SLActivity
import kotlinx.android.synthetic.main.actitity_xrecycler_view.*

/**
 * @package: com.mirstone.andframe.module
 * @fileName: XRecyclerViewAct
 * @data: 2018/8/6 9:23
 * @author: ShiLiang
 * @describe:
 */
class XRecyclerViewAct : SLActivity(){
    var itemNum: Int = 0
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitity_xrecycler_view)
//        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setPullRefreshEnabled(true)
        recyclerView.setLoadingMoreEnabled(true)

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate)
        recyclerView.defaultRefreshHeaderView.setRefreshTimeVisible(true)
        recyclerView.defaultFootView.setLoadingHint("自定义加载中提示")
        recyclerView.defaultFootView.setNoMoreHint("自定义加载完毕提示")
        recyclerView.setLimitNumberToCallLoadMore(5)
        val list = ArrayList<String>()
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                recyclerView.refreshComplete()
            }

            override fun onLoadMore() {
                Handler().postDelayed({
                    addData(list,10)
                    adapter.notifyDataSetChanged()
                    recyclerView.loadMoreComplete()
                }, 300)
            }

        })

        addData(list,20)
        adapter = MyAdapter(list)
        recyclerView.adapter = adapter
        val imageView = ImageView(this)
        imageView.setImageResource(R.mipmap.ic_launcher)
        recyclerView.addHeaderView(imageView)

        val imageView2 = ImageView(this)
        imageView2.setImageResource(R.mipmap.ic_launcher)
        recyclerView.addHeaderView(imageView2)

        recyclerView.setNoMore(true)
        recyclerView.setNoMore(false)
    }

    private fun addData(list: ArrayList<String>, count:Int) {
        for (i in itemNum..itemNum + count) {
            itemNum++
            list.add(String.format("这是第%d个Item", i))
        }
    }
}