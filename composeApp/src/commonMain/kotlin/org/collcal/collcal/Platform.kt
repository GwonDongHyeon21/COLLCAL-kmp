package org.collcal.collcal

enum class PlatformType {
    ANDROID,
    WEB,
    IOS
}

expect fun getPlatformType(): PlatformType
