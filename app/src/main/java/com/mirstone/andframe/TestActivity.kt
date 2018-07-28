package com.mirstone.andframe

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * @package: com.mirstone.andframe
 * @fileName: TestActivity
 * @data: 2018/7/26 14:17
 * @author: ShiLiang
 * @describe:
 */
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        ViewCompat.setTransitionName(textView, "zoomImageView")
        textView.setImageResource(R.mipmap.icon_guide_page_0)
    }
}
