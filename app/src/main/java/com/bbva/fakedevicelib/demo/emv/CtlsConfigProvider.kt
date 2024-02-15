package com.bbva.fakedevicelib.demo.emv

import com.bbva.devicelib.emv.configData.ETag
import com.bbva.devicelib.emv.configData.Tlv

object CtlsConfigProvider {

    val amex = listOf<Tlv>()
       /* listOf(
                Tlv(ETag.CTLS_AE_READER_CAPABILITY.tagData, "C8"),
                Tlv(ETag.CTLS_AE_ENHANCED_READER_CAPABILITY.tagData, "D8E00000"),
                Tlv(ETag.CTLS_AE_SUPPORT_DELAYED_AUTH.tagData, "00"),
                Tlv(ETag.CTLS_AE_DRL_SETS.tagData, "00"),
                Tlv(ETag.CTLS_AE_GO_ONLINE.tagData, "00"),
                Tlv(ETag.CTLS_AE_UN_RANGE.tagData, "60")
              )
        */

    val mc = listOf<Tlv>()
       /* listOf(
                Tlv(ETag.CTLS_MC_CARD_DATA_INPUT_CAPABILITY.tagData, "60"),
                Tlv(ETag.CTLS_MC_CVM_CAPABILITY.tagData, "60"),
                Tlv(ETag.CTLS_MC_NO_CVM_CAPABILITY.tagData, "08"),
                Tlv(ETag.CTLS_MC_SECURITY_CAPABILITY.tagData, "C8"),
                Tlv(ETag.CTLS_MC_MAG_STRIPE_CVM_CAPABILITY.tagData, "00"),
                Tlv(ETag.CTLS_MC_MAG_STRIPE_NO_CVM_CAPABILITY.tagData, "00"),
                Tlv(ETag.CTLS_MC_KERNEL_CONFIGURATION.tagData, "B0"),
                Tlv(ETag.CTLS_MC_MAX_NUMBER_TRANS_LOG.tagData, "02")
              )
        */

    val visa = listOf<Tlv>()
        //listOf(Tlv(ETag.CTLS_VISA_TTQ.tagData, "36204000"))
}
