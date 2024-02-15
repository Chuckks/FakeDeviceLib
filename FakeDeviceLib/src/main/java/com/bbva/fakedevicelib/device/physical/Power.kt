package com.bbva.fakedevicelib.device.physical

import com.bbva.devicelib.physical.IPower

class Power: IPower {
    override fun reboot() { }
    override fun savingPower() { }

    override fun shutdown() { }
    override fun sleep() { }
}