package com.bbva.fakedevicelib

import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.emv.outputData.Tsi
import com.bbva.devicelib.emv.outputData.Tvr
import com.bbva.devicelib.physical.ICard
import com.bbva.devicelib.physical.IPinpad

object FakeConfig {
    var cardDetected: ICard.EType = ICard.EType.ICC
    var multiApp: Boolean = false
    var cvmSignature: Boolean = true
    var cvmPin: Boolean = true
    var cardRefuse: Boolean = false
    var txResult: IEmvProcess.EResult = IEmvProcess.EResult.SUCCESS
    var error: IEmvProcess.EError = IEmvProcess.EError.NONE
    var pinpadResult: IPinpad.EResult = IPinpad.EResult.SUCCESS
    var tvr: Tvr = Tvr()
    var tsi: Tsi = Tsi()
}