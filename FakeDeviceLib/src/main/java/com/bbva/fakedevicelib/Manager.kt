package com.bbva.fakedevicelib

import android.content.Context
import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.connection.ISdkConnect

private val TAG = Constant.DEV_PREFIX + Manager::class.java.simpleName

internal class Manager: ISdkConnect {

    private var connected = false

    override fun connect(context: Context): Boolean {
        Log.i(TAG, "CONNECTING SERVICE...")
        connected = true
        return true
    }

    override fun disconnect(context: Context): Boolean {
        Log.i(TAG, "DISCONNECT SERVICE...")
        connected = false
        return true
    }

    override fun isConnected() = connected
}
