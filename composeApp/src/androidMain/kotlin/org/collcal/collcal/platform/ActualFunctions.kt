package org.collcal.collcal.platform

import android.content.Context
import org.collcal.collcal.MainActivity.Companion.context

actual fun getPlatformType(): PlatformType = PlatformType.ANDROID
actual fun getToken(): String {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("userToken", "") ?: ""
}