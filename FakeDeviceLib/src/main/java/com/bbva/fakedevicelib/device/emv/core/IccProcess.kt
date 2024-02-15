package com.bbva.fakedevicelib.device.emv.core

import com.bbva.devicelib.Constant

private val TAG = Constant.EMV_PREFIX + IccProcess::class.java.simpleName

class IccProcess(private val processData: ProcessData): ICardProcess {

    private val emvListener = EmvListener(processData)

    override fun init() = true

    override fun stop() {}

    override fun start(): Boolean {
        TransactProcess(emvListener, processData.inputData).start()
        return true
    }

    override fun setSelectApp(selectedApp: Int) {
        emvListener.appSelected?.invoke(selectedApp)
    }

    override fun confirmTrxData(userConfirm: Boolean) {
        emvListener.confirm = userConfirm
    }

    override fun isSignatureCaptured(value: Boolean) {
    }
}
