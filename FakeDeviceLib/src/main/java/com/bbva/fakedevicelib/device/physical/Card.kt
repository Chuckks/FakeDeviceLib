package com.bbva.fakedevicelib.device.physical

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.physical.ICard
import com.bbva.devicelib.physical.data.CardConfig

private val TAG = Constant.DEV_PREFIX + Card::class.java.simpleName

class Card: ICard {
    var present = false

    override var data: String = ""
        set(value) {
            Log.i(TAG, "Data = [$value]")
            field = value
        }

    override var step = ICard.EStep.INIT
        set(value) {
            Log.i(TAG, "Step = [$value]")
            field = value
        }

    override var type = ICard.EType.UNKNOWN
        set(value) {
            Log.i(TAG, "CardType = [$value]")
            field = value
        }

    override fun cancelCheckCard() { }
    override fun cardOff() { }

    override fun checkCard(cardConfig: CardConfig): Boolean {
        step = ICard.EStep.READING

        cardConfig.cardSupported.forEach {
            Log.i(TAG, "checkCard available: ${it.name}")
        }

        Thread.sleep(2000)
        step == ICard.EStep.FINISH
        return true
    }

    override fun isAvailable() = true
    override fun isPresent() = present

}