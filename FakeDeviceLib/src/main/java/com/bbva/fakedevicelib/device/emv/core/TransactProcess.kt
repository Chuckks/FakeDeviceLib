package com.bbva.fakedevicelib.device.emv.core

import com.bbva.devicelib.emv.inputData.InputData
import com.bbva.devicelib.emv.inputData.enums.EFlowType
import com.bbva.devicelib.emv.outputData.Application
import com.bbva.fakedevicelib.FakeConfig

private val mcCandidate = Application().apply {
    id = "A0000000041010"
    preferredName = "MASTERCARD CREDIT"
    label = "MASTERCARD CREDIT"
    name = "MASTERCARD CREDIT"
}

private val visaCandidate = Application().apply {
    id = "A0000000031010"
    preferredName = "VISA CREDIT"
    label = "VISA CREDIT"
    name = "VISA CREDIT"
}

private val amexCandidate = Application().apply {
    id = "A00000002501"
    preferredName = "AMEX"
    label = "AMEX"
    name = "AMEX"
}

class TransactProcess(private val emvListener: IEmvListener, private val inputData: InputData) {

    private val listCandidates: List<Application> =
        listOf(mcCandidate, visaCandidate, amexCandidate)

    fun start() {
        if (FakeConfig.multiApp)
            emvListener.onWaitAppSelect(listCandidates, true)

        emvListener.onAppFinalSelect("A0000000041010")

        emvListener.onConfirmCardNo("4000340000000504")
        Thread.sleep(1000)

        if (inputData.flowType == EFlowType.FULL_GRADE) {
            emvListener.onCardDataExchangeComplete()
            Thread.sleep(1000)

            if (FakeConfig.cvmPin) {
                emvListener.onRequestShowPinPad(0, -1)
                Thread.sleep(1000)
            }

            if (FakeConfig.cvmSignature) {
                emvListener.onRequestSignature()
            }
        }

        emvListener.onOnlineProc()
        Thread.sleep(1000)


        emvListener.onTransResult(FakeConfig.txResult, null)
        Thread.sleep(1000)
    }
}
