package com.bbva.fakedevicelib

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.IEmvProcessView
import com.bbva.devicelib.emv.inputData.AmountData
import com.bbva.devicelib.emv.inputData.CvmOptions
import com.bbva.devicelib.emv.inputData.HostResponseData
import com.bbva.devicelib.emv.inputData.InputData
import com.bbva.devicelib.emv.inputData.PinpadConfig
import com.bbva.devicelib.emv.inputData.TerminalData
import com.bbva.devicelib.emv.inputData.enums.EFlowType
import com.bbva.devicelib.emv.inputData.enums.EOnlineStatus
import com.bbva.devicelib.emv.inputData.enums.EPinOption
import com.bbva.devicelib.emv.outputData.OutputData
import com.bbva.devicelib.emv.outputData.PanData
import com.bbva.devicelib.physical.ICard
import com.bbva.devicelib.physical.data.CardConfig
import com.bbva.fakedevicelib.databinding.ActivityEmvViewBinding
import com.bbva.fakedevicelib.databinding.FragmentCardPresentBinding
import com.bbva.fakedevicelib.fragments.CardPresentFragment
import com.bbva.fakedevicelib.fragments.IdleFragment
import com.bbva.utilitieslib.extensions.format
import com.bbva.utilitieslib.extensions.toast
import kotlinx.coroutines.*
import java.util.*

private val TAG = Constant.BBVA_PREFIX + EmvViewActivity::class.java.simpleName

class EmvViewActivity: AppCompatActivity(), IEmvProcessView {

    private lateinit var binding: ActivityEmvViewBinding
    private var emvProcess = App.deviceCenter.emvProcess

    private val fragmentManager: FragmentManager = supportFragmentManager
    private val idleFragment = IdleFragment()
    private val inputData = fillInputData()

    private fun fillInputData(): InputData {
        val pinpad = PinpadConfig().apply {
            keyIndex = 2
        }

        val amount = AmountData().apply {
            amount = "1000"
            otherAmount = "000"
            currencyCode = "0484"
            currencyExponent = 2
        }

        val terminal = TerminalData().apply {
            terminalId = "20408960"
            merchantId = "12345678913654"
            currencyCode = "0484"
        }

        val card = CardConfig().apply {
            cardSupported = setOf(ICard.EType.ICC, ICard.EType.MAGNETIC, ICard.EType.CTLS)
        }

        return InputData().apply {
            amountData = amount
            terminalData = terminal
            cardConfig = card
            pinpadConfig = pinpad
            pinOptions = EPinOption.DECIDE_CARD
            flowType = EFlowType.FULL_GRADE
            cvmOptions = CvmOptions(pinOffline = true, signature = true, pinOnline = true, noCvm = true, mobileCvm = true)
        }
    }

    private val dateHost: String = "01/02/2024" // TODO Tomar fecha del host
    private val reference: String = "123456" // TODO Tomar de la respuesta del Host

    private fun connectServices() {
        lifecycleScope.launch(Dispatchers.IO) {
            Log.i(TAG, "Connect Manager Device")
            delay(1000L)
            ReadConfig(App.deviceCenter.emvConfig, resources).load(false) //TODO ESTO DEBE SER INDEPENDIENTE DE LA MAQUINA
        }
    }

    //@implements IEmvProcess
    private fun start() {
        emvProcess.init(this)
        emvProcess.start(inputData)
    }


    private fun stop() {
        Log.i(TAG, "finalProcess")
        Thread.sleep(1000)
        emvProcess.stop()
        Log.i(TAG, "Stop Process")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmvViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectServices()
        fragmentManager.beginTransaction().add(R.id.fragmentContainerView, idleFragment).commit()
        idleFragment.listener = View.OnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                start()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i(TAG, "onBackPressed")
        stop()
    }

    override fun displaySupportedCards(cardsAvailable: Set<ICard.EType>) {
        val cardPresent = CardPresentFragment()

        cardsAvailable.forEach{
            Log.i(TAG, "Card Available [${it.name}]")
        }

        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainerView, cardPresent).commit()
        cardPresent.listener = View.OnClickListener {
            stop()
        }
    }

    override fun displayDetectedCard(cardType: ICard.EType) {
        Log.i(TAG, "displayDetectedCard")
        val result = when (cardType) {
            ICard.EType.UNKNOWN  -> {
                stop()
                "TimeOut / Cancel / Unreable"
            }

            ICard.EType.MAGNETIC -> "Tarjeta Magnetica Detectada"
            ICard.EType.ICC      -> "Tarjeta Chip Detectada"
            ICard.EType.CTLS     -> "Tarjeta NFC Detectada"
        }

        lifecycleScope.launch(Dispatchers.Main) {
            this@EmvViewActivity.toast(result)
        }
    }

    override fun promptUserToSelectApp(names: List<String>) {
        Log.i("promptUserToSelectApp", "")
        val count = 1;
        names.forEach {
            Log.i(TAG, "$count.- $it")
        }

        emvProcess.setSelectApp(0)
    }

    override fun captureUserSignature() {
        Log.i(TAG, "captureUserSignature")
        emvProcess.isSignatureCaptured(true)
    }

    override fun promptUserForPin() {
        Log.i(TAG, "promptUserForPin")
        val maskCard = emvProcess.outputData.cardData.pan.getMaskedPan()
        val amount = inputData.amountData.toFormatString()
    }

    override fun promptUserToConfirmCardNumber(pan: PanData) {
        Log.i(TAG, "promptUserToConfirmCardNumber")
        val maskCard = pan.getMaskedPan()
        val amount = inputData.amountData.toFormatString()

        emvProcess.confirmTrxData(true)
        //emvProcess.confirmTrxData(false)
    }


    override fun sendTransactionRequest(outputData: OutputData): HostResponseData {
        Log.i(TAG, "sendTransactionRequest")
        if (outputData.cardType != ICard.EType.MAGNETIC)
            lifecycleScope.launch(Dispatchers.Main) {
                this@EmvViewActivity.toast("No Retire Tarjeta")
            }

        val tagsResponse = "8A0230308906313233343536"
        Log.i(TAG, "Serial tagsResponse-> [$tagsResponse]")

        return HostResponseData(onlineStatus = EOnlineStatus.APPROVAL, tvlList = tagsResponse)
    }

    override fun displaySuccessTransaction() {
        Log.i(TAG, "displaySuccessTransaction")

        val maskCard = emvProcess.outputData.cardData.pan.getMaskedPan()
        val amount = inputData.amountData.toFormatString()
        val hour = Date().format("HH:mm:ss", TimeZone.getDefault())
        val auth = "996633"
        val ticket = "1223344556"
        val date = Date().format("dd/MM/yyyy", TimeZone.getDefault())

        Log.i(TAG, "MaskCard [$maskCard] - Amount [$amount] - Hour [$hour] - Auth [$auth] - Ticket [$ticket] - Date [$date]")
        lifecycleScope.launch(Dispatchers.Main) {
            this@EmvViewActivity.toast("Operation Success")
        }
        stop()
    }

    override fun displayFailedTransaction() {
        Log.i(TAG, "displayFailedTransaction")
        lifecycleScope.launch(Dispatchers.Main) {
            this@EmvViewActivity.toast("La Transacción no se Completo")
        }
        stop()
    }

    override fun displayTransactionError(errorCode: IEmvProcess.EError) {
        Log.i(TAG, "displayTransactionError ${errorCode.name}")

        when (errorCode) {
            IEmvProcess.EError.TRY_AGAIN -> {
                lifecycleScope.launch(Dispatchers.Main) {
                    this@EmvViewActivity.toast("TRY_AGAIN [${errorCode.name}]")
                }
                emvProcess.stop()
                emvProcess.start(inputData)
            }

            IEmvProcess.EError.SEE_PHONE -> {
                lifecycleScope.launch(Dispatchers.Main) {
                    this@EmvViewActivity.toast("Siga las Instrucciones de su Teléfono")
                }
                emvProcess.stop()
                emvProcess.start(inputData)
            }

            else                         -> {
                lifecycleScope.launch(Dispatchers.Main) {
                    this@EmvViewActivity.toast("Error[${errorCode.name}]")
                }
                stop()
            }
        }
    }

    override fun promptUserToRemoveCard() {
        lifecycleScope.launch(Dispatchers.Main) {
            this@EmvViewActivity.toast("Por Favor, Retire su Tarjeta.")
        }
        Log.i(TAG, "promptUserToRemoveCard")
    }

}

