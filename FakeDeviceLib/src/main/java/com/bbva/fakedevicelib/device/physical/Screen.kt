package com.bbva.fakedevicelib.device.physical

import android.content.pm.ActivityInfo
import com.bbva.devicelib.Constant
import com.bbva.devicelib.physical.IScreen


private val TAG = Constant.DEV_PREFIX + Screen::class.java.simpleName

class Screen: IScreen {

    override fun setExclusiveMode(exclusive: Boolean) {}

    override fun setDropDown(enable: Boolean) {}

    override fun setNavigationVisibility(visible: Boolean) {}

    override fun hideNavigationItems(vararg items: IScreen.ENavigator) {
        var sum = 0
        for (item in items)
            sum += getNavigatorId(item)
    }

    private fun getNavigatorId(value: IScreen.ENavigator): Int {
        return when (value) {
            IScreen.ENavigator.NONE   -> 0
            IScreen.ENavigator.HOME   -> 1
            IScreen.ENavigator.BACK   -> 2
            IScreen.ENavigator.RECENT -> 3
        }
    }

    private fun getPosition(position: IScreen.EPosition): Int {
        return when (position) {
            IScreen.EPosition.RIGHT -> ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
            IScreen.EPosition.LEFT  -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            IScreen.EPosition.UP    -> ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
            IScreen.EPosition.DOWN  -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}
