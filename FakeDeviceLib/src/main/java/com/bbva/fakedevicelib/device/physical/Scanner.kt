package com.bbva.fakedevicelib.device.physical

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.physical.IScan

private val TAG = Constant.DEV_PREFIX + Scanner::class.java.simpleName

class Scanner: IScan {
    override fun getResult(): String = ""
    override fun isAvailable(): Boolean = true

    override fun start() {
        Log.e(TAG, "Device Start")
    }

    override fun stop() {
        Log.e(TAG, "Device Stop")
    }
}