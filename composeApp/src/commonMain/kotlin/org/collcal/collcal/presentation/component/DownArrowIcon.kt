package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val DownArrowIcon = ImageVector.Builder(
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Gray),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 10f)
        lineTo(12f, 15f)
        lineTo(17f, 10f)
        close()
    }
}.build()