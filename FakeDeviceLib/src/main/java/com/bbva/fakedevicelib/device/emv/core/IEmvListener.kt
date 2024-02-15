package com.bbva.fakedevicelib.device.emv.core

import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.outputData.Application

interface IEmvListener {

    fun onWaitAppSelect(var1: List<Application>?, var2: Boolean)
    fun onAppFinalSelect(var1: String?)

    fun onConfirmCardNo(var1: String?)
    fun onRequestShowPinPad(var1: Int, var2: Int)

    fun onRequestSignature()
    fun onCertVerify(var1: Int, var2: String?)

    fun onOnlineProc()
    fun onCardDataExchangeComplete()

    fun onTransResult(var1: IEmvProcess.EResult, var2: String?)
    fun onConfirmationCodeVerified()

    fun onRequestDataExchange(var1: String?)
    fun onTermRiskManagement()
}
