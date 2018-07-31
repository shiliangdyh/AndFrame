package com.mirstone.andframe

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.mirstone.baselib.SLActivity
import com.mirstone.baselib.dialog.SLDialog
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.listener.OnCheckedListener
import com.zhihu.matisse.listener.OnSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : SLActivity() {
    private val REQUEST_CODE_CHOOSE = 23
    val REQUEST_CODE = 0x000000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image.setOnClickListener({
            //            val intent = Intent(this, TestActivity::class.java)
//            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, image, "zoomImageView").toBundle())

//            startActivity(intent)
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
            SLDialog().show(supportFragmentManager)
        })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                SLFilePicker()
//                        .withActivity(this)
//                        .withRequestCode(REQUEST_CODE)
//                        .withMaxNum(3)
//                        .withMutiMode(true)
////                        .withStartPath(cacheDir.absolutePath)
//                        .start()

                Matisse.from(this@MainActivity)
                        .choose(MimeType.ofAll(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(
                                CaptureStrategy(true, "com.mirstone.andframe.fileprovider"))
                        .maxSelectable(9)
                        .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(
                                resources.getDimensionPixelSize(R.dimen.default_dialog_margin))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        // for glide-V3
                        //                                            .imageEngine(new GlideEngine())
                        // for glide-V4
                        .imageEngine(Glide4Engine())
                        .setOnSelectedListener(object : OnSelectedListener {
                            override fun onSelected(
                                    uriList: List<Uri>, pathList: List<String>) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("onSelected", "onSelected: pathList=$pathList")

                            }
                        })
                        .originalEnable(true)
                        .maxOriginalSize(10)
                        .setOnCheckedListener(object : OnCheckedListener {
                            override fun onCheck(isChecked: Boolean) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("isChecked", "onCheck: isChecked=$isChecked")
                            }
                        })
                        .forResult(REQUEST_CODE_CHOOSE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val list = data.getStringArrayListExtra("paths")
            Log.d("MainActivity", Arrays.toString(list.toArray()))
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == Activity.RESULT_OK && data != null) {
            var uris = Matisse.obtainResult(data)
            var urls = Matisse.obtainPathResult(data)
            Log.d("MainActivity", Arrays.toString(urls.toTypedArray()))
        }
    }

    override fun isEnableGesture() = false

}
