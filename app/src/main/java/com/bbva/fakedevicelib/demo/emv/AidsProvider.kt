package com.bbva.fakedevicelib.demo.emv

import com.bbva.devicelib.emv.configData.AidData

object AidsProvider {

    val aidsmx = listOf(
        AidData().apply {
            name = "MC Debit/Credit"
            aid = "A0000000041010"
            version = "0002"
            ddol = "9F3704"
            tdol = "9F02065F2A029A039C0195059F3704"

            floorLimit = "000000000000"
            checkCvmLimit = false
            cvmLimit = "000000008000"
            tacDefault = "FE50BCA000"
            tacOnline = "FE50BCF800"
            tacDenial = "0000000000"

            ctlsCheckTransLimit = true
            ctlsTransLimit = "000000080000"

            ctlsCheckCvmLimit = true
            ctlsCvmLimit = "000000001000"

            ctlsCheckFloorLimit = true
            ctlsFloorLimit = "000000000000"

            ctlsCdCvmLimit = "000000060000"
            ctlsNoCdCvmLimit = "000000080000"

            ctlsRiskData = "6C7C800000000000"

            ctlsTacDefault = "0000000000"
            ctlsTacOnline = "F45084800C"
            ctlsTacDenial = "F45084800C"
        },

        AidData().apply {
            name = "Maestro"
            aid = "A0000000043060"
            version = "0002"
            ddol = "9F3704"
            tdol = "9F02065F2A029A039C0195059F3704"

            floorLimit = "000000000000"
            checkCvmLimit = false
            cvmLimit = "000000008000"
            tacDefault = "FE50BCA000"
            tacOnline = "FE50BCF800"
            tacDenial = "0000000000"

            ctlsCheckTransLimit = true
            ctlsTransLimit = "000000080000"

            ctlsCheckCvmLimit = true
            ctlsCvmLimit = "000000001000"

            ctlsCheckFloorLimit = true
            ctlsFloorLimit = "000000000000"

            ctlsCdCvmLimit = "000000060000"
            ctlsNoCdCvmLimit = "000000080000"

            ctlsRiskData = "4C7C800000000000"

            ctlsTacDefault = "0000800000"
            ctlsTacOnline = "F45004800C"
            ctlsTacDenial = "F45004800C"
        },

        AidData().apply {
            name = "Visa Debit/Credit"
            aid = "A0000000031010"
            version = "008C"
            ddol = "9F3704"
            tdol = "9F02065F2A029A039C0195059F3704"

            floorLimit = "000000000000"
            checkCvmLimit = false
            cvmLimit = "000000008000"
            tacDefault = "DC4000A800"
            tacOnline = "DC4004F800"
            tacDenial = "0010000000"

            ctlsCheckTransLimit = true
            ctlsTransLimit = "000000001000"

            ctlsCheckCvmLimit = true
            ctlsCvmLimit = "000000001000"

            ctlsCheckFloorLimit = true
            ctlsFloorLimit = "000000000000"

            ctlsCdCvmLimit = "000000060000"
            ctlsNoCdCvmLimit = "000000080000"

            ctlsRiskData = "6CF0000000000000"

            ctlsTacDefault = "DC4000A800"
            ctlsTacOnline = "DC4004F800"
            ctlsTacDenial = "0010000000"
        },

        AidData().apply {
            name = "Visa Electron"
            aid = "A0000000032010"
            version = "008C"
            ddol = "9F3704"
            tdol = "9F02065F2A029A039C0195059F3704"

            floorLimit = "000000000000"
            checkCvmLimit = false
            cvmLimit = "000000008000"
            tacDefault = "DC4000A800"
            tacOnline = "DC4004F800"
            tacDenial = "0010000000"

            ctlsCheckTransLimit = true
            ctlsTransLimit = "000000080000"

            ctlsCheckCvmLimit = true
            ctlsCvmLimit = "000000001000"

            ctlsCheckFloorLimit = true
            ctlsFloorLimit = "000000000000"

            ctlsCdCvmLimit = "000000060000"
            ctlsNoCdCvmLimit = "000000080000"

            ctlsRiskData = "6CF0000000000000"

            ctlsTacDefault = "DC4000A800"
            ctlsTacOnline = "DC4004F800"
            ctlsTacDenial = "0010000000"
        },

        AidData().apply {
            name = "Amex"
            aid = "A00000002501"
            version = "0001"
            ddol = "9F3704"

            floorLimit = "000000000000"
            checkCvmLimit = false
            cvmLimit = "000000008000"
            tacDefault = "DC50FC9800"
            tacOnline = "DE00FC9800"
            tacDenial = "0010000000"

            ctlsCheckTransLimit = true
            ctlsTransLimit = "000000080000"

            ctlsCheckCvmLimit = true
            ctlsCvmLimit = "000000001000"

            ctlsCheckFloorLimit = true
            ctlsFloorLimit = "000000000000"

            ctlsCdCvmLimit = "000000060000"
            ctlsNoCdCvmLimit = "000000080000"

            ctlsTacDefault = "DC50FC9800"
            ctlsTacOnline = "DE00FC9800"
            ctlsTacDenial = "0010000000"
        })
}