package com.bbva.fakedevicelib.device.physical

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.physical.IPinpad
import com.bbva.devicelib.physical.data.PinpadData
import com.bbva.devicelib.physical.data.PinpadText
import com.bbva.fakedevicelib.FakeConfig

private val TAG = Constant.DEV_PREFIX + Pinpad::class.java.simpleName

class Pinpad : IPinpad {

    var pinpadData = PinpadData()
    var pinpadTextData = PinpadText()

    private var pinblock = "12345678"//TODO implementar PinBlock

    override fun capture(panValue: String, onCaptureResult: (result: IPinpad.EResult) -> Unit) {
        onCaptureResult(FakeConfig.pinpadResult)
    }

    override fun config(pinpad: PinpadData) {
        pinpadData = pinpad
    }

    override fun config(pinpad: PinpadData, pinpadText: PinpadText) {
        pinpadData = pinpad
        pinpadTextData = pinpadText
    }

    override fun config(pinpadText: PinpadText) {
        pinpadTextData = pinpadText
    }

    override fun getPinBlock(): String {
        Log.i(TAG, "pinblock $pinblock")
        return pinblock
    }

    override fun isAvailable(): Boolean = true
}