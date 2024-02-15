package com.bbva.fakedevicelib

import android.content.res.Resources
import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvConfig
import com.bbva.devicelib.emv.configData.AidData
import com.bbva.devicelib.emv.configData.CapkData
import com.bbva.devicelib.emv.configData.CtlsSpecificData
import com.bbva.devicelib.emv.configData.TerminalParam
import com.bbva.devicelib.emv.configData.Tlv
import com.bbva.devicelib.emv.configData.TlvList
import com.bbva.fakedevicelib.utilities.ReadConfig
import com.bbva.utilitieslib.extensions.getFileToString
import com.bbva.utilitieslib.utils.Json


private val TAG = Constant.DEV_PREFIX + ReadConfig::class.java.simpleName

class ReadConfig(emvConfig: IEmvConfig, private val resources: Resources): ReadConfig(emvConfig) {

    override fun readAidList(): List<AidData> {
        return Json.toList(resources.getFileToString(R.raw.aids))
    }

    override fun readCapkList(): List<CapkData> = Json.toList(resources.getFileToString(R.raw.capks))
    override fun readTerminalParams(): TerminalParam = Json.fromJson<TerminalParam>(resources.getFileToString(R.raw.terminal)) as TerminalParam

    override fun readSpecificConfig(): HashMap<IEmvConfig.EType, TlvList> {
        val ctlsProvider = Json.fromJson<CtlsSpecificData>(resources.getFileToString(R.raw.specific_data)) as CtlsSpecificData//todo resolver unpack a hashMap
        return hashMapOf(
                IEmvConfig.EType.ICC to getIccProvider(ctlsProvider),
                IEmvConfig.EType.PAYPASS to getCtlsProviderMc(ctlsProvider),
                IEmvConfig.EType.PAYWAVE to getCtlsProviderVisa(ctlsProvider),
                IEmvConfig.EType.EXPRESSPAY to getCtlsProviderAmex(ctlsProvider)
                        )
    }

    private fun getIccProvider(ctlsProvider: CtlsSpecificData) = TlvList().apply {
        ctlsProvider.icc.forEach { mc ->
            add(Tlv(mc.tag, mc.value))
            Log.i(TAG, "getIccProvider [${mc.name}]")
        }
    }

    private fun getCtlsProviderMc(ctlsProvider: CtlsSpecificData) = TlvList().apply {
        ctlsProvider.paypass.forEach { mc ->
            add(Tlv(mc.tag, mc.value))
            Log.i(TAG, "getCltsProviderMc [${mc.name}]")
        }
    }

    private fun getCtlsProviderVisa(ctlsProvider: CtlsSpecificData) = TlvList().apply {
        ctlsProvider.paywave.forEach { visa ->
            add(Tlv(visa.tag, visa.value))
            Log.i(TAG, "getCltsProviderVisa [${visa.name}]")
        }
    }

    private fun getCtlsProviderAmex(ctlsProvider: CtlsSpecificData) = TlvList().apply {
        ctlsProvider.expresspay.forEach { amex ->
            add(Tlv(amex.tag, amex.value))
            Log.i(TAG, "getCltsProviderAmex [${amex.name}]")
        }
    }
}
