package com.michael.pluginwarehouse

import android.view.View


class JvmTest {

    fun test(view: View, click: ClickMonitor): Boolean {
        click.onClick(view)
        return false
    }

    fun interface ClickMonitor {
        fun onClick(view: View)
    }

}