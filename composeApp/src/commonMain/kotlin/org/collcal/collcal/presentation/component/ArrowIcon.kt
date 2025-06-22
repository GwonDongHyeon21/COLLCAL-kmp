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

val LeftArrowIcon = ImageVector.Builder(
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
        moveTo(16f, 4f)
        lineTo(8f, 12f)
        lineTo(16f, 20f)
        lineTo(17.5f, 18.5f)
        lineTo(10.5f, 12f)
        lineTo(17.5f, 5.5f)
        close()
    }
}.build()