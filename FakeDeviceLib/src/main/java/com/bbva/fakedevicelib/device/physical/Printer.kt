package com.bbva.fakedevicelib.device.physical

import android.graphics.Bitmap
import com.bbva.devicelib.physical.IPrinter

class Printer: IPrinter {

    override var bold: Boolean = false
    override var underline: Boolean = false

    override var align: IPrinter.EAlign = IPrinter.EAlign.CENTER
    override var font: IPrinter.EFont = IPrinter.EFont.NORMAL

    init {
        reset()
    }

    override fun barCode(
        data: String,
        typeBarcode: IPrinter.EBarCode,
        height: Int,
        width: Int,
        showText: Boolean
    ) {
    }

    override fun command(data: ByteArray) { }
    override fun format(font: IPrinter.EFont, bold: Boolean, align: IPrinter.EAlign) {
        this.font = font
        this.bold = bold
        this.align = align
    }

    override fun image(bitmap: Bitmap) {  }
    override fun image(value: ByteArray) { }
    override fun init() { }

    override fun isAvailable(): Boolean = true
    override fun lineFeed(num: Int) { }

    override fun qrCode(data: String, size: Int) { }
    override fun reset() { }

    override fun text(text: String) { }
    override fun textLine(text: String) { }
}