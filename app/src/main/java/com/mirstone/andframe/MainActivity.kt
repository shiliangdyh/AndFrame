package com.mirstone.andframe

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.mirstone.baselib.SLActivity
import com.mirstone.selectfilelib.SLFilePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : SLActivity() {
    val REQUEST_CODE = 0x000000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image.setOnClickListener({
            //            val intent = Intent(this, TestActivity::class.java)
//            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, image, "zoomImageView").toBundle())

//            startActivity(intent)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SLFilePicker()
                        .withActivity(this)
                        .withRequestCode(REQUEST_CODE)
                        .withMaxNum(3)
                        .withMutiMode(true)
//                        .withStartPath(cacheDir.absolutePath)
                        .start()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val list = data.getStringArrayListExtra("paths")
            Log.d("MainActivity", Arrays.toString(list.toArray()))
        }
    }

    override fun isEnableGesture() = true

}
