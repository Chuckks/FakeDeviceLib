package com.bbva.fakedevicelib

import android.content.Context
import com.bbva.devicelib.module.IDeviceCenter
import com.bbva.devicelib.module.IDeviceCenterCreator

class FakeDeviceCenterCreator: IDeviceCenterCreator {
    override fun create(context: Context): IDeviceCenter = FakeDeviceCenter(context)

}
