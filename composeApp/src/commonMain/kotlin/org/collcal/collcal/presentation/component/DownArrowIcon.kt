package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.black

val DownArrowIcon = ImageVector.Builder(
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 10f)
        lineTo(12f, 15f)
        lineTo(17f, 10f)
        close()
    }
}.build()