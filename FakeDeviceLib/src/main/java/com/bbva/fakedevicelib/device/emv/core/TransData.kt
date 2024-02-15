package com.bbva.fakedevicelib.device.emv.core

import com.bbva.devicelib.emv.inputData.enums.EFlowType
import com.bbva.devicelib.emv.inputData.enums.ETrans
import com.bbva.devicelib.physical.ICard

data class TransData(
        val amount: String,
        val otherAmount: String,
        val cardType: ICard.EType,
        val transType: ETrans,
        val flowType: EFlowType
)
