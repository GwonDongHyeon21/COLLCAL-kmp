package org.collcal.collcal.platform

import org.collcal.collcal.MainActivity.Companion.token

actual fun getPlatformType(): PlatformType = PlatformType.ANDROID
actual fun getToken(): String {
    return token
}