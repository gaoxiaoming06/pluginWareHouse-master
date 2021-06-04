package com.michael.pluginwarehouse

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTestClickListener).setOnClickListener(object :
            View.OnClickListener {
            override fun onClick(v: View?) {
                Exception().printStackTrace()
                Log.i(TAG, "setCircleData: ")
            }

        })
        findViewById<Button>(R.id.btnTestBackLamba).setOnClickListener {
            Exception().printStackTrace()
            Log.d(TAG, "-------btnTestBackLamba------")
        }

        findViewById<Button>(R.id.btnTestBlock).setOnClickListener {
            Exception().printStackTrace()
            Log.d(TAG, "-------btnTestBlock------")
        }

        findViewById<Button>(R.id.btnTestBlock2222).setOnClickListener(this)

        JvmTest().test(findViewById<Button>(R.id.btnTestBackLamba),object : JvmTest.ClickMonitor {
            override fun onClick(view: View) {
                Log.d(TAG, "-------JvmTest------")
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTestBlock2222 -> {
                Exception().printStackTrace()
                Log.d(TAG, "-------btnTestBlock2222------")
            }
        }
    }
}