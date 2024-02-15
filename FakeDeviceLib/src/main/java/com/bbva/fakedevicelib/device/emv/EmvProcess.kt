package com.bbva.fakedevicelib.device.emv

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.IEmvProcessView
import com.bbva.devicelib.emv.inputData.InputData
import com.bbva.devicelib.physical.ICard
import com.bbva.fakedevicelib.FakeConfig
import com.bbva.fakedevicelib.device.emv.core.ICardProcess
import com.bbva.fakedevicelib.device.emv.core.IccProcess
import com.bbva.fakedevicelib.device.emv.core.MagProcess
import com.bbva.fakedevicelib.device.emv.core.ProcessData
import java.security.InvalidParameterException


private val TAG = Constant.EMV_PREFIX + EmvProcess::class.simpleName

class EmvProcess(private val processData: ProcessData): IEmvProcess {

    override val outputData = processData.outputData
    private lateinit var cardProcess: ICardProcess

    override fun init(emvView: IEmvProcessView) {
        processData.emvView = emvView
    }

    override fun isAvailable() = true

    override fun isInit(): Boolean = true

    private fun removeCard() {
        if (processData.cardDevice.isPresent()) {
            processData.emvView.promptUserToRemoveCard()

            while (processData.cardDevice.isPresent()) {
                processData.soundDevice.warningBeep()
                Thread.sleep(500)
            }
        }
    }

    override fun stop() {
        removeCard()
        processData.cardDevice.cancelCheckCard()

        if (this::cardProcess.isInitialized) {
            cardProcess.stop()
        }
    }

    override fun start(inputData: InputData): Boolean {
        processData.emvView.displaySupportedCards(inputData.cardConfig.cardSupported)
        Thread.sleep(2000)

        val cardType = FakeConfig.cardDetected
        Log.i(TAG, "Card Detected [$cardType] !!!")

        processData.outputData.cardType = cardType
        processData.emvView.displayDetectedCard(cardType)

        cardProcess = when (cardType) {
            ICard.EType.CTLS,
            ICard.EType.ICC      -> IccProcess(processData)
            ICard.EType.MAGNETIC -> MagProcess(processData)
            else                 -> throw InvalidParameterException("CardType [$cardType] wrong")

        }
        cardProcess.init()
        cardProcess.start()

        return true
    }

    override fun confirmTrxData(userConfirm: Boolean) {
        if (this::cardProcess.isInitialized) {
            cardProcess.confirmTrxData(userConfirm)
        }
    }

    override fun isSignatureCaptured(value: Boolean) {
        if (this::cardProcess.isInitialized) {
            cardProcess.isSignatureCaptured(value)
        }
    }

    override fun setSelectApp(selectedApp: Int) {
        if (this::cardProcess.isInitialized) {
            cardProcess.setSelectApp(selectedApp)
        }
    }
}