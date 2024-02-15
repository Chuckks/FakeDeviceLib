package com.bbva.fakedevicelib.device.emv.core

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.IEmvProcessView
import com.bbva.devicelib.emv.inputData.InputData
import com.bbva.devicelib.emv.outputData.OutputData
import com.bbva.devicelib.physical.ICard
import com.bbva.devicelib.physical.IPinpad
import com.bbva.devicelib.physical.ISound
import com.bbva.devicelib.physical.data.PinpadData
import com.bbva.utilitieslib.utils.TimeSpan


private val TAG = Constant.EMV_PREFIX + ProcessData::class.java.simpleName

data class ProcessData(
    val cardDevice: ICard,
    val soundDevice: ISound,
    val pinpadDevice: IPinpad
                      ) {

    var inputData = InputData()
    var outputData = OutputData()

    lateinit var emvView: IEmvProcessView

    fun capturePinpad(offline: Boolean): IPinpad.EResult {
        val pinpadData = PinpadData().apply {
            this.offline = offline
            this.dukpt = inputData.pinpadConfig.dukpt
            this.timeout = inputData.pinpadConfig.timeout
        }

        var result = IPinpad.EResult.INIT

        pinpadDevice.config(pinpadData)
        pinpadDevice.capture(outputData.cardData.pan.number) { result = it }


        Log.i(TAG, "Captured PIN")
        Thread.sleep(1000)

        Log.i(TAG, "PIN new result $result")
        return result
    }
}
