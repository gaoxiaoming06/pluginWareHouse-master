package com.michael.pluginwarehouse

object FastClickUtil1111 {
    private const val FAST_CLICK_TIME_DISTANCE = 300
    private var sLastClickTime: Long = 0

    fun isFastDoubleClick(): Boolean {
        val time = System.currentTimeMillis()
        val timeDistance = time - sLastClickTime
        if (0 < timeDistance && timeDistance < FAST_CLICK_TIME_DISTANCE) {
            return true
        }
        sLastClickTime = time
        return false
    }
}