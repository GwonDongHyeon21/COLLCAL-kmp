package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.black

val VCheckIcon = ImageVector.Builder(
    name = "VCheckIcon",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(8f, 8f)
        lineTo(12f, 16f)
        lineTo(16f, 8f)
        lineTo(15f, 8f)
        lineTo(12f, 14f)
        lineTo(9f, 8f)
        close()
    }
}.build()