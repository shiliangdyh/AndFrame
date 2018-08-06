package com.mirstone.andframe.module

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.mirstone.andframe.R

/**
 * @package: com.mirstone.xrecyclerdemo
 * @fileName: MyAdapter
 * @data: 2018/8/5 21:11
 * @author: ShiLiang
 * @describe:
 */
class MyAdapter(var data: List<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recycler, viewGroup, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(myHolder: MyHolder, i: Int) {
        myHolder.itemView.setOnClickListener { Toast.makeText(myHolder.itemView.context, "position = $i", Toast.LENGTH_SHORT).show() }
        myHolder.itemView.setOnLongClickListener {
            Toast.makeText(myHolder.itemView.context, "onLong = $i", Toast.LENGTH_SHORT).show()
            true
        }
        myHolder.itemView as TextView
        myHolder.itemView.text = data[i]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
