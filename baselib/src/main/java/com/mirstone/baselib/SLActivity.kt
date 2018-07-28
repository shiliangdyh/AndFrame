package com.mirstone.baselib

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.mirstone.baselib.util.ScreenUtil
import me.imid.swipebacklayout.lib.SwipeBackLayout

import me.imid.swipebacklayout.lib.app.SwipeBackActivity

/**
 * @package: com.mirstone.baselib
 * @fileName: SLActivity
 * @data: 2018/7/23 15:22
 * @author: ShiLiang
 * @describe:
 */
open class SLActivity : SwipeBackActivity() {
    private var frameLayout: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.frameLayout = findViewById(android.R.id.content) as FrameLayout;
        addPlaceBarIfNeed()
        initSwipeConfig()
    }

    private fun initSwipeConfig() {
        val swipeBackLayout = swipeBackLayout
        swipeBackLayout.setEnableGesture(isEnableGesture())
        swipeBackLayout.setEdgeTrackingEnabled(getEdgeTrackingEnabled())
        swipeBackLayout.addSwipeListener(object : SwipeBackLayout.SwipeListener {
            override fun onScrollStateChange(state: Int, scrollPercent: Float) {

            }

            override fun onEdgeTouch(edgeFlag: Int) {
//                vibrate(VIBRATE_DURATION.toLong())
            }

            override fun onScrollOverThreshold() {
//                vibrate(VIBRATE_DURATION.toLong())
            }
        })
    }

    /**
     * 添加状态栏替换View
     */
    private fun addPlaceBarIfNeed() {
        val placeBar = View(this)
        placeBar.setBackgroundColor(getPlaceBarColor())
        val placeBarHeight = getPlaceBarHeight()
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, placeBarHeight)
        placeBar.layoutParams = params
        frameLayout!!.addView(placeBar)
    }

    private fun getPlaceBarHeight(): Int {
        var placeBarHeight = 0
        if (Build.VERSION.SDK_INT > 19) {
            placeBarHeight = ScreenUtil.getStatusBarHeight(this)
        }
        return placeBarHeight
    }

    override fun setContentView(layoutResID: Int) {
        val view = layoutInflater.inflate(layoutResID, frameLayout, false)
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.setMargins(0, getPlaceBarHeight(), 0, 0)
        view.layoutParams = params
        frameLayout!!.addView(view)
    }

//    private fun vibrate(duration: Long) {
//        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        val pattern = longArrayOf(0, duration)
//        vibrator.vibrate(pattern, -1)
//    }

    companion object {
        private const val VIBRATE_DURATION = 10
    }

    open protected fun isEnableGesture() = true

    open protected fun getEdgeTrackingEnabled() = SwipeBackLayout.EDGE_LEFT

    open protected fun getPlaceBarColor() = Color.TRANSPARENT
}
