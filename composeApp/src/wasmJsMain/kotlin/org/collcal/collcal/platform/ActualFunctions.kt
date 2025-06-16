package org.collcal.collcal.platform

actual fun getPlatformType(): PlatformType = PlatformType.WEB
actual fun getToken(): String {
    return ""
}