package com.bbva.fakedevicelib.device.emv

import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.emv.IEmvConfig
import com.bbva.devicelib.emv.configData.AidData
import com.bbva.devicelib.emv.configData.CapkData
import com.bbva.devicelib.emv.configData.DrlData
import com.bbva.devicelib.emv.configData.TerminalParam
import com.bbva.devicelib.emv.configData.TlvList

private val TAG = Constant.EMV_PREFIX + EmvConfig::class.simpleName

class EmvConfig: IEmvConfig {
    override fun setSpecificConfig(type: IEmvConfig.EType, tlvList: TlvList) {
        specificConfigMap[type] = tlvList
    }

    override fun setSpecificConfig(config: HashMap<IEmvConfig.EType, TlvList>) {
        specificConfigMap = config
    }

    //@DATA
    private fun setTerminalData(param: TerminalParam): Boolean {
        terminalParam = param
        val result = true
        Log.i(TAG, "TerminalData Result[$result]")
        return result
    }

    private enum class ECheckConfig(val code: Int) {
        FAIL(-1),
        SUCCESS(0),
        ONLY_AID(1),
        ONLY_CAPK(2);

        companion object {

            fun isCapkLoaded(code: Int) = (code == ONLY_CAPK.code || code == SUCCESS.code)
            fun isAidLoaded(code: Int) = (code == ONLY_AID.code || code == SUCCESS.code)
        }
    }

    //@CAPKS
    private fun addCapk(capk: CapkData): Boolean {
        capkList.add(capk.copy())
        val result = true
        Log.i(TAG, "Add CAPK[${capk.rid}] -> INDEX [${capk.index}] -> Result [$result]")
        return result
    }


    private fun addCapks(capks: List<CapkData>) = capks.forEach { capk -> addCapk(capk) }

    private fun cleanCapks() = true
    override fun isCapksLoaded() = true

    //@AIDS
    private fun addAid(aid: AidData): Boolean {
        aidsList.add(aid.copy())
        val result = true
        Log.i(TAG, "Add AID[${aid}] -> Result [$result]")

        return result
    }

    private fun addAids(aids: List<AidData>) = aids.forEach { aid -> addAid(aid) }

    override fun isAidsLoaded() = true
    override fun isAvailable() = true
    private fun cleanAids() = true

    override fun clean(): Boolean {
        if (!cleanCapks()) {
            Log.e(TAG, "Can't Clean CAPKS")
            return false
        }
        return if (!cleanAids()) {
            Log.e(TAG, "Can't Clean CAPKS")
            false
        }
        else {
            Log.i(TAG, "Clean All Successful")
            loadConfig = false
            true
        }
    }

    override fun setConfig(terminalParams: TerminalParam, aids: List<AidData>, capks: List<CapkData>): Boolean {
        if (!clean())
            return false

        addAids(aidsList)
        addCapks(capkList)

        setTermParam()
        deleteDrlLimit()
        //addDrlsLimit(aidsList)

        if (!setTerminalData(terminalParams)) {
            Log.e(TAG, "Clean Set Terminal Data")
            return false
        }

        Log.i(TAG, "Set All Config Successful")
        loadConfig = true
        return true
    }

    //@Drl
    private fun setTermParam() {}
    private fun deleteDrlLimit() = true

    private fun addDrlLimit(aid: DrlData) {
        val result = true
        Log.i(TAG, "Add DRL[${aid}] -> Result [$result]")
    }

   // override fun setDrlsLimit(drls: List<DrlData>) = drls.forEach { addDrlLimit(it) }
    override fun isEmpty() = !loadConfig

    companion object {

        val aidsList: MutableList<AidData> = mutableListOf()
        val capkList: MutableList<CapkData> = mutableListOf()

        var terminalParam: TerminalParam = TerminalParam()
        var specificConfigMap = HashMap<IEmvConfig.EType, TlvList>()

        var loadConfig = true
    }
}