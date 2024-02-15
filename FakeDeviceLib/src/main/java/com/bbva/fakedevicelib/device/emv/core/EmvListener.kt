package com.bbva.fakedevicelib.device.emv.core

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.inputData.HostResponseData
import com.bbva.devicelib.emv.outputData.Application
import com.bbva.devicelib.emv.outputData.OutputData
import com.bbva.devicelib.emv.outputData.PanData
import com.bbva.fakedevicelib.FakeConfig
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.APP_FINAL_SELECT
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.CARD_DATA_EXCHANGE_COMPLETE
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.CERT_VERIFY
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.CONFIRMATION_CODE_VERIFIED
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.CONFIRM_CARD_NO
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.NOT_INIT
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.ONLINE_PROC
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.REQUEST_DATA_EXCHANGE
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.REQUEST_SHOW_PINPAD
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.REQUEST_SIGNATURE
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.TERMINAL_RISK_MANAGER
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.TRANS_RESULT
import com.bbva.fakedevicelib.device.emv.core.EmvListener.EStep.WAIT_APP_SELECT
import com.bbva.utilitieslib.extensions.toBoolean
import kotlin.properties.Delegates

private val TAG = Constant.EMV_PREFIX + EmvListener::class.java.simpleName
private const val SELECTED_ERROR = -1

class EmvListener(private val processData: ProcessData): IEmvListener {

    var step = NOT_INIT
        private set(value) {
            Log.i(TAG, "Step = [${value.name}]")
            field = value
        }

    var result = IEmvProcess.EResult.NONE
        private set(value) {
            Log.i(TAG, "Result = [${value.name}]")
            field = value
        }

    var confirm: Boolean by Delegates.observable(false) { _, _, newValue ->
        if (newValue)
            checkPartialGrade()
        else
            statusError("cardNumber is refused")
    }

    var appSelected: ((selected: Int) -> Unit)? = null

    private var response: HostResponseData = HostResponseData()

    enum class EStep {
        NOT_INIT,
        WAIT_APP_SELECT,
        APP_FINAL_SELECT,
        CONFIRM_CARD_NO,
        REQUEST_SHOW_PINPAD,
        REQUEST_SIGNATURE,
        TERMINAL_RISK_MANAGER,
        CERT_VERIFY,
        ONLINE_PROC,
        CARD_DATA_EXCHANGE_COMPLETE,
        CONFIRMATION_CODE_VERIFIED,
        REQUEST_DATA_EXCHANGE,
        TRANS_RESULT,
        FINAL_PROCESS
    }

    private var tsi = FakeConfig.tsi
    private var tvr = FakeConfig.tvr

    private fun transactionInfo() =
        Log.i(TAG, "TSI [${tsi}] - TVR [${tvr}]")

    private fun stepStatusInfo() {
        Log.i(TAG, "Step: [$step] - Result [$result]")
    }

    private fun emptyCandidateList(): Int {
        Log.e(TAG, "Empty Candidate List")

        result = IEmvProcess.EResult.ERROR
        return SELECTED_ERROR
    }

    private fun onlyOneApplication(apps: List<Application>): Int {
        val selected = 0
        Log.i(TAG, "Only One App [${getPreferredName(apps[selected])}]")
        return selected
    }

    private fun userSelectApplication(apps: List<Application>): Int {
        Log.i(TAG, "userSelectApplication")

        val appSelection = List(apps.size) { position ->
            val name = getPreferredName(apps[position])
            Log.i(TAG, "Application $position: [$name]")
            name
        }

        var selected = SELECTED_ERROR
        processData.emvView.promptUserToSelectApp(appSelection)

        appSelected = {
            selected = it

            if (selected != SELECTED_ERROR) {
                //processData.outputData.iccData.app.preferredName = appSelection[selected]
            }
            else {
                result = IEmvProcess.EResult.ERROR
            }
        }

        return selected
    }

    private fun getPreferredName(app: Application): String {
        if (app.label.isNotEmpty())
            return app.label

        if (app.name.isNotEmpty())
            return app.name

        return app.preferredName.ifEmpty { "" }
    }

    override fun onWaitAppSelect(apps: List<Application>?, firstSelect: Boolean) {
        step = WAIT_APP_SELECT
        Log.i(TAG, "onWaitAppSelect -> Counter [${apps?.size}] - FirstSelect [$firstSelect]")

        apps?.let { list ->
            val selected = when (list.size) {
                0    -> emptyCandidateList()
                1    -> onlyOneApplication(list)
                else -> userSelectApplication(list)
            }

            Log.i(TAG, "user selected: $selected")
        } ?: onTransResult(IEmvProcess.EResult.ERROR, "USUARIO CANCELO!")

        stepStatusInfo()
    }

    private fun statusError(message: String): Boolean {
        Log.e(TAG, message)
        result = IEmvProcess.EResult.ERROR
        return false
    }

    override fun onAppFinalSelect(aidSelected: String?) {
        step = APP_FINAL_SELECT
        Log.i(TAG, "onAppFinalSelect -> aidSelected [$aidSelected]")

        aidSelected?.let {
            //processData.outputData.iccData.app.id = aidSelected
        } ?: statusError("idSelected is Null Or Empty")


        transactionInfo()
        stepStatusInfo()
    }


    private fun checkPartialGrade(): Boolean {
        //TODO crear EFlowType
       /* if (processData.inputData.flowType != EFlowType.PARTIAL_GRADE)
            return false
*/
        return true//processData.emvView.sendTransactionRequest(fillOutputData()).onlineStatus == HostResponseData.EOnlineStatus.APPROVAL
    }

    private fun fillOutputData(): OutputData {
//todo ooooooooooooooooooooooooooooooooooo
        return OutputData()
    }

    override fun onConfirmCardNo(cardNumber: String?) {
        step = CONFIRM_CARD_NO
        Log.i(TAG, "onConfirmCardNo -> Card Number [$cardNumber]")

        cardNumber?.let { pan ->
            //processData.outputData.cardData.pan = PanData(pan)
            processData.emvView.promptUserToConfirmCardNumber(PanData())//processData.outputData.cardData.pan)

        } ?: statusError("cardNumber is Null Or Empty")

        transactionInfo()
        stepStatusInfo()
    }

    override fun onRequestShowPinPad(pinType: Int, remainTimes: Int) {
        step = REQUEST_SHOW_PINPAD
        Log.i(TAG, "onRequestShowPinPad -> pinType [$pinType] remainTimes [$remainTimes]")

        processData.emvView.promptUserForPin()//showInputPin()
        processData.capturePinpad(pinType.toBoolean())

        transactionInfo()
        stepStatusInfo()
    }

    override fun onRequestSignature() {
        step = REQUEST_SIGNATURE
        Log.i(TAG, "onRequestSignature")

        processData.emvView.captureUserSignature()
        Thread.sleep(2000)

        transactionInfo()
        stepStatusInfo()
    }

    override fun onCertVerify(param0: Int, param1: String?) {
        step = CERT_VERIFY
        Log.i(TAG, "onCertVerify -> param0 [$param0] param1 [$param1]")

        transactionInfo()
        stepStatusInfo()
    }

    override fun onOnlineProc() {
        step = ONLINE_PROC
        response = processData.emvView.sendTransactionRequest(fillOutputData())
        //Log.i(TAG, "Response Host [${response.onlineStatus.name}]")

       // val tlvList = TlvList.parser(response.tlvList)
       // Log.i(TAG, "Tlv List [$tlvList]")

        transactionInfo()
        stepStatusInfo()
    }

    override fun onCardDataExchangeComplete() {
        step = CARD_DATA_EXCHANGE_COMPLETE
        Log.i(TAG, "onCardDataExchangeComplete")

        transactionInfo()
        stepStatusInfo()
    }

    override fun onTransResult(resultCode: IEmvProcess.EResult, resultMessage: String?) {
        var result = resultCode
        step = TRANS_RESULT
        Log.i(TAG, "onTransResult -> resultCode [$resultCode] - resultMessage [$resultMessage]")

        transactionInfo()
        stepStatusInfo()

        if (FakeConfig.cardRefuse) {
            result = IEmvProcess.EResult.ERROR
            FakeConfig.error = IEmvProcess.EError.CARD_REFUSED_SYNC
        }

        if (FakeConfig.error != IEmvProcess.EError.NONE)
            processData.emvView.displayTransactionError(FakeConfig.error)
        else {
            when (result) {
                IEmvProcess.EResult.OFFLINE_APPROVAL,
                IEmvProcess.EResult.ONLINE_APPROVAL,
                IEmvProcess.EResult.SUCCESS        -> processData.emvView.displaySuccessTransaction()

                IEmvProcess.EResult.ONLINE_DENIAL,
                IEmvProcess.EResult.OFFLINE_DENIAL -> processData.emvView.displayFailedTransaction()

                IEmvProcess.EResult.TRY_AGAIN      -> processData.emvView.displayTransactionError(IEmvProcess.EError.TRY_AGAIN)
                else                   -> processData.emvView.displayTransactionError(FakeConfig.error)
            }
        }
    }

    override fun onConfirmationCodeVerified() {
        step = CONFIRMATION_CODE_VERIFIED
        Log.i(TAG, "onConfirmationCodeVerified")

        processData.emvView.displayTransactionError(IEmvProcess.EError.SEE_PHONE)

        transactionInfo()
        stepStatusInfo()
    }

    override fun onRequestDataExchange(cardNumber: String?) {
        step = REQUEST_DATA_EXCHANGE
        Log.i(TAG, "onRequestDataExchange -> cardNumber [$cardNumber]")

        transactionInfo()
        stepStatusInfo()
    }

    override fun onTermRiskManagement() {
        step = TERMINAL_RISK_MANAGER
        Log.i(TAG, "onTermRiskManagement")

        transactionInfo()
        stepStatusInfo()
    }
}
