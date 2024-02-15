package com.bbva.fakedevicelib.demo.emv

import com.bbva.devicelib.emv.IEmvConfig
import com.bbva.devicelib.emv.configData.TlvList
import com.bbva.fakedevicelib.utilities.ReadConfig

class ProviderReadConfig(emvConfig: IEmvConfig): ReadConfig(emvConfig) {

    override fun readAidList() = AidsProvider.aidsmx
    override fun readCapkList() = CapkProvider.capksTest

    override fun readSpecificConfig(): HashMap<IEmvConfig.EType, TlvList> = hashMapOf()
    override fun readTerminalParams() = TerminalParamProvider.mx
}
