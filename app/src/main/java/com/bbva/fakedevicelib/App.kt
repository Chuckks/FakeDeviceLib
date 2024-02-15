package com.bbva.fakedevicelib

import android.app.Application
import android.util.Log
import com.bbva.devicelib.module.IDeviceCenter

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i("APP", "Oncrate")
        deviceCenter = DeviceCenterFactory().create(DeviceCenterFactory.EDevType.FAKE, applicationContext)
        deviceCenter.connect()

        deviceCenter.permissions.getAllPermissions().forEach {
            Log.i("APP", "Permission [$it]")
        }
    }

    companion object {

        lateinit var deviceCenter: IDeviceCenter
    }
}
