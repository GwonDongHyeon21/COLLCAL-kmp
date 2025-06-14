package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.black

val ArrowBackIcon = ImageVector.Builder(
    name = "ArrowBackIcon",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(10f, 4f)
        lineTo(4f, 12f)
        lineTo(10f, 20f)
        lineTo(11.5f, 18.5f)
        lineTo(6.5f, 12f)
        lineTo(11.5f, 5.5f)
        close()
    }
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(6f, 11f)
        lineTo(20f, 11f)
        lineTo(20f, 13f)
        lineTo(6f, 13f)
        close()
    }
}.build()

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
        moveTo(5f, 6f)
        lineTo(12f, 18f)
        lineTo(19f, 6f)
        close()
    }
}.build()

val UpArrowIcon = ImageVector.Builder(
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(5f, 18f)
        lineTo(12f, 6f)
        lineTo(19f, 18f)
        close()
    }
}.build()

val RightArrowIcon = ImageVector.Builder(
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(black),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(8f, 4f)
        lineTo(16f, 12f)
        lineTo(8f, 20f)
        lineTo(6.5f, 18.5f)
        lineTo(13.5f, 12f)
        lineTo(6.5f, 5.5f)
        close()
    }
}.build()