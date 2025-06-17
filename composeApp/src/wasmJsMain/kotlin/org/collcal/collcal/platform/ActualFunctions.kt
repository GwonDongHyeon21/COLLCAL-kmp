package org.collcal.collcal.platform

import org.collcal.collcal.token

actual fun getPlatformType(): PlatformType = PlatformType.WEB
actual fun getToken(): String {
    return token
}