package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1

val PencilIcon = ImageVector.Builder(
    name = "PencilIcon",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 2f)
        lineTo(17f, 2f)
        lineTo(17f, 22f)
        lineTo(7f, 22f)
        close()
    }
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(12f, 2f)
        lineTo(12f, 0f)
        lineTo(14f, 2f)
        close()
    }
    path(
        fill = SolidColor(gray1),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 22f)
        lineTo(17f, 22f)
        lineTo(12f, 18f)
        close()
    }
}.build()