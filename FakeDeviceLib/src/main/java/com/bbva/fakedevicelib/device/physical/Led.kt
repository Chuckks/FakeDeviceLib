package com.bbva.fakedevicelib.device.physical

import com.bbva.devicelib.physical.ILed

class Led: ILed {

    private var colorOnArray = BooleanArray(5) { false }

    init {
        turnOffAllLights()
    }

    private fun setColor(color: ILed.EColor, on: Boolean) {
        colorOnArray[color.ordinal] = on
    }

    override fun isAvailable(): Boolean = true

    override fun isColorOn(color: ILed.EColor): Boolean = colorOnArray[color.ordinal]

    override fun switch() {
        for (color in ILed.EColor.values()) {
            switchLight(color)
        }
    }

    override fun switchLight(color: ILed.EColor) = if (isColorOn(color)) turnOff(color) else turnOn(color)

    override fun switchLights(vararg colors: ILed.EColor) {
        colors.forEach {
            switchLight(it)
        }
    }

    override fun turnOn(color: ILed.EColor) = setColor(color, true)
    override fun turnOff(color: ILed.EColor) = setColor(color, false)

    override fun turnOffAllLights() = turnOffLights(*ILed.EColor.values())

    override fun turnOffLights(vararg colors: ILed.EColor) {
        colors.forEach {
            turnOff(it)
        }
    }

    override fun turnOnAllLights() = turnOnLights(*ILed.EColor.values())

    override fun turnOnLights(vararg colors: ILed.EColor) {
        colors.forEach {
            turnOn(it)
        }
    }
}