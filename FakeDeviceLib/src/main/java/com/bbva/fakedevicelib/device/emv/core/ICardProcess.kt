package com.bbva.fakedevicelib.device.emv.core

interface ICardProcess {

    fun init(): Boolean
    fun start(): Boolean
    fun setSelectApp(selectedApp: Int)
    fun confirmTrxData(userConfirm: Boolean)
    fun isSignatureCaptured(value: Boolean)
    fun stop()
}
