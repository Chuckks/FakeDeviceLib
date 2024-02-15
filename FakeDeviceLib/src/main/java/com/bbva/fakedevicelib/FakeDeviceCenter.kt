package com.bbva.fakedevicelib

import android.content.Context
import com.bbva.devicelib.emv.IEmvConfig
import com.bbva.devicelib.emv.IEmvProcess
import com.bbva.devicelib.module.IDeviceCenter
import com.bbva.devicelib.physical.ICard
import com.bbva.devicelib.physical.IComms
import com.bbva.devicelib.physical.IHsm
import com.bbva.devicelib.physical.IInfo
import com.bbva.devicelib.physical.ILed
import com.bbva.devicelib.physical.IPermissions
import com.bbva.devicelib.physical.IPinpad
import com.bbva.devicelib.physical.IPower
import com.bbva.devicelib.physical.IPrinter
import com.bbva.devicelib.physical.IScan
import com.bbva.devicelib.physical.IScreen
import com.bbva.devicelib.physical.ISoftwareInstall
import com.bbva.devicelib.physical.ISound
import com.bbva.fakedevicelib.device.emv.EmvConfig
import com.bbva.fakedevicelib.device.emv.EmvProcess
import com.bbva.fakedevicelib.device.emv.core.ProcessData
import com.bbva.fakedevicelib.device.physical.Card
import com.bbva.fakedevicelib.device.physical.Hsm
import com.bbva.fakedevicelib.device.physical.Info
import com.bbva.fakedevicelib.device.physical.Led
import com.bbva.fakedevicelib.device.physical.Permissions
import com.bbva.fakedevicelib.device.physical.Pinpad
import com.bbva.fakedevicelib.device.physical.Power
import com.bbva.fakedevicelib.device.physical.Printer
import com.bbva.fakedevicelib.device.physical.Scanner
import com.bbva.fakedevicelib.device.physical.Screen
import com.bbva.fakedevicelib.device.physical.Sound


class FakeDeviceCenter(val context: Context): IDeviceCenter {

    private val manager = Manager()

    override val led: ILed = Led()
    override val info: IInfo = Info()

    override val power: IPower = Power()
    override val scan: IScan = Scanner()

    override val printer: IPrinter = Printer()
    override val screen: IScreen = Screen()

    override val pinpad: IPinpad = Pinpad()
    override val sound: ISound = Sound()

    override val card: ICard = Card()
    override val hsm: IHsm = Hsm()

    override val permissions: IPermissions = Permissions()
    override val emvConfig: IEmvConfig = EmvConfig()

    override val emvProcess: IEmvProcess = EmvProcess(ProcessData(card, sound, pinpad))

    override val softwareInstall: ISoftwareInstall
        get() = TODO("Not yet implemented")

    override val comm: IComms
        get() = TODO("Not yet implemented")

    override fun connect() = manager.connect(context)
    override fun disconnect() = manager.disconnect(context)
    override fun isConnected() = manager.isConnected()

}
