package com.bbva.fakedevicelib

import android.content.Context
import com.bbva.devicelib.module.IDeviceCenter

class DeviceCenterFactory {
    enum class EDevType {
        INGENICO,
        FAKE
    }

    fun create(type: EDevType, context: Context): IDeviceCenter {
        return when (type) {
            EDevType.FAKE -> FakeDeviceCenterCreator().create(context)
            else           -> throw Exception("No yet Implemented [${type.name}]")
            }
        }
}
