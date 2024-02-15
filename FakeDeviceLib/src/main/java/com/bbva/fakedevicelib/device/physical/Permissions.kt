package com.bbva.fakedevicelib.device.physical

import com.bbva.devicelib.physical.IPermissions

class Permissions : IPermissions{

    override fun getAllPermissions() =
        mapOf(
            "com.fake.perm.LED" to true,
            "com.fake.perm.MSR" to true,
            "com.fake.perm.ICC" to true,
            "com.fake.perm.PINPAD" to true,
            "com.fake.perm.SCREEN" to true,
            "com.fake.perm.SECURITY" to true,
            "com.fake.perm.CONTACTLESS_CARD" to true,
        )
}