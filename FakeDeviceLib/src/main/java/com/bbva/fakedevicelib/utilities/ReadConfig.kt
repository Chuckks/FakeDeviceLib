package com.bbva.fakedevicelib.utilities

import android.util.Log
import com.bbva.devicelib.emv.IEmvConfig
import com.bbva.devicelib.emv.configData.AidData
import com.bbva.devicelib.emv.configData.CapkData
import com.bbva.devicelib.emv.configData.TerminalParam
import com.bbva.devicelib.emv.configData.TlvList

abstract class ReadConfig(private val emvConfig: IEmvConfig) {

    protected abstract fun readAidList(): List<AidData>
    protected abstract fun readCapkList(): List<CapkData>
    protected abstract fun readSpecificConfig(): HashMap<IEmvConfig.EType, TlvList>
    protected abstract fun readTerminalParams(): TerminalParam

    fun load(mandatory: Boolean = false) {
        Log.i("Readconfig", "Start")
        if (mandatory || emvConfig.isEmpty()) {

            emvConfig.setConfig(readTerminalParams(), readAidList(), readCapkList())
            emvConfig.setSpecificConfig(readSpecificConfig())
        }
    }
}
