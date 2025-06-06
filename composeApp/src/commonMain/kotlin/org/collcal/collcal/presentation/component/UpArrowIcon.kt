package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.gray13

val UpArrowIcon = ImageVector.Builder(
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(gray13),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 14f)
        lineTo(12f, 9f)
        lineTo(17f, 14f)
        close()
    }
}.build()