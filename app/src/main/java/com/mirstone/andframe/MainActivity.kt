package com.mirstone.andframe

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mirstone.andframe.module.XRecyclerViewAct
import com.mirstone.baselib.SLActivity
import com.mirstone.baselib.util.WindowUtil


class MainActivity : SLActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowUtil.setStatusBarFontIconDark(this,true)
    }

    @Override
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_xrecycler -> {
                startActivity(Intent(this, XRecyclerViewAct::class.java))
            }
            else -> {
            }
        }
    }

    override fun isEnableGesture() = false

}
