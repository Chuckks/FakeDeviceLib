package com.bbva.fakedevicelib.demo.emv

import com.bbva.devicelib.emv.configData.TerminalParam

object TerminalParamProvider {

    val mx = TerminalParam().apply {
        capability = "60F8C8"
        addCapability = "7200F0E001"
        currencyCode = "0484"
        countryCode = "0484"
        terminalType = "22"
        currencyExp = "02"
        forceOnline = false
    }
}
