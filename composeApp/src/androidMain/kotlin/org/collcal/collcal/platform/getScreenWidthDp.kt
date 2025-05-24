package org.collcal.collcal.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun getScreenWidthDp(): Int {
    return LocalConfiguration.current.screenWidthDp
}