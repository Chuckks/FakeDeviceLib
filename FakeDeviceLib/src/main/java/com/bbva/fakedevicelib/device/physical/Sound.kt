package com.bbva.fakedevicelib.device.physical

import com.bbva.devicelib.physical.ISound

class Sound: ISound {
    override fun infoBeep() = custom(1, 440, 2000, 20)

    override fun errorBeep() = custom(4, 440, 2000, 20)

    override fun successBeep() = custom(2, 440, 2000, 20)

    override fun warningBeep() = custom(3, 440, 2000, 20)

    override fun custom(count: Int, freq: Int, duration: Int, interval: Int) {}

    override fun isAvailable() = true
}