package com.bbva.fakedevicelib.device.emv.core

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.inputData.HostResponseData
import com.bbva.devicelib.emv.inputData.enums.EOnlineStatus
import com.bbva.devicelib.emv.inputData.enums.EPinOption
import com.bbva.devicelib.emv.outputData.CardData
import com.bbva.devicelib.emv.outputData.PanData
import com.bbva.devicelib.emv.outputData.PemData
import com.bbva.fakedevicelib.FakeConfig

private val DEFAULT_PEM = PemData()

private val TAG = Constant.EMV_PREFIX + MagProcess::class.simpleName

class MagProcess(private val processData: ProcessData): ICardProcess {

    private var requiredPin = false
    private var cardData = CardData()

    override fun init() =
        try {
            cardData = CardData.parser("%B4000340000000504^John/Doe                  ^22251050000123000?;4000340000000504=22251050000000001230?")
            true
        }
        catch (e: Exception) {
            Log.e(TAG, e.toString())
            processData.emvView.displayTransactionError(IEmvProcess.EError.TRACK_BAD_FORMAT)
            false
        }

    override fun start(): Boolean {

        if (FakeConfig.error != IEmvProcess.EError.NONE) {
            processData.emvView.displayTransactionError(FakeConfig.error)
            return false
        }

        if (!processData.inputData.cardConfig.fallBack && !cardData.checkMagneticPermit()) {
            processData.emvView.displayTransactionError(IEmvProcess.EError.MAG_NOT_ALLOW)
            return false
        }

        if (!cardData.checkExpirationDate()) {
            processData.emvView.displayTransactionError(IEmvProcess.EError.EXPIRED_DATE)
            return false
        }

        fillOutputData()
        if (!checkPinOption()) {
            processData.emvView.displayTransactionError(IEmvProcess.EError.INPUT_PIN)
            return false
        }

        processData.emvView.promptUserToConfirmCardNumber(PanData())//processData.outputData.cardData.pan)
        return true
    }

    override fun setSelectApp(selectedApp: Int) {
    }

    override fun confirmTrxData(userConfirm: Boolean) {
        if (!userConfirm) {
            processData.emvView.displayTransactionError(IEmvProcess.EError.CARD_NUM_REFUSED)
        }
        if (!requiredPin) {
            processData.emvView.captureUserSignature()
            Log.w(TAG, "Capture Signature")
        }
       else if (processData.emvView.sendTransactionRequest(processData.outputData).onlineStatus == EOnlineStatus.APPROVAL)
            processData.emvView.displaySuccessTransaction()
        else
            processData.emvView.displayFailedTransaction()
    }

    override fun isSignatureCaptured(value: Boolean) {
        processData.emvView.displaySuccessTransaction()
        if (value && processData.emvView.sendTransactionRequest(processData.outputData).onlineStatus == EOnlineStatus.APPROVAL)
            processData.emvView.displaySuccessTransaction()
        else
            processData.emvView.displayFailedTransaction()
    }

    override fun stop() {
        Log.i(TAG, "Stop")
    }

    private fun capturePin(): Boolean {
        processData.emvView.promptUserForPin()
        processData.capturePinpad(false)
        Thread.sleep(3000)
        processData.outputData.pinBlock = processData.pinpadDevice.getPinBlock()
        return true
    }

    private fun checkPinOption(): Boolean {
        requiredPin = when (processData.inputData.pinOptions) {
            EPinOption.MANDATORY   -> true
            EPinOption.DECIDE_CARD -> cardData.checkDecideCard()

            EPinOption.DISABLED    -> {
                Log.i(TAG, "Option Pin Disable")
                return true
            }

        }

        return if (requiredPin) {
            Log.w(TAG, "Capture PIN")
            capturePin()
        }
        else {
            processData.emvView.captureUserSignature()
            Log.w(TAG, "Capture Signature")
            true
        }
    }

    private fun fillOutputData() {
        processData.outputData.pemData = DEFAULT_PEM
        processData.outputData.cardData = cardData
    }
}
