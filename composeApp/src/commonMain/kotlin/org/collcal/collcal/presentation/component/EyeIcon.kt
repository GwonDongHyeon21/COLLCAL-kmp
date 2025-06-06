package org.collcal.collcal.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.unit.dp

val EyeIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Eye",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        addPath(
            pathData = listOf(
                PathNode.MoveTo(1f, 12f),
                PathNode.QuadTo(12f, 3f, 23f, 12f),
                PathNode.QuadTo(12f, 21f, 1f, 12f),
                PathNode.Close
            ),
            fill = SolidColor(Color.Black),
            fillAlpha = 0.2f,
            stroke = null,
        )
        addPath(
            pathData = listOf(
                PathNode.MoveTo(12f, 8f),
                PathNode.RelativeArcTo(
                    horizontalEllipseRadius = 4f,
                    verticalEllipseRadius = 4f,
                    theta = 0f,
                    isMoreThanHalf = true,
                    isPositiveArc = true,
                    arcStartDx = 0f,
                    arcStartDy = 8f
                ),
                PathNode.RelativeArcTo(
                    horizontalEllipseRadius = 4f,
                    verticalEllipseRadius = 4f,
                    theta = 0f,
                    isMoreThanHalf = true,
                    isPositiveArc = true,
                    arcStartDx = 0f,
                    arcStartDy = -8f
                ),
                PathNode.Close
            ),
            fill = SolidColor(Color.Black)
        )
    }.build()

val EyeOffIcon: ImageVector
    get() = ImageVector.Builder(
        name = "EyeOffIcon",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        // 눈 윤곽 (연하게)
        addPath(
            pathData = listOf(
                PathNode.MoveTo(1f, 12f),
                PathNode.QuadTo(12f, 3f, 23f, 12f),
                PathNode.QuadTo(12f, 21f, 1f, 12f),
                PathNode.Close
            ),
            fill = SolidColor(Color.Black),
            fillAlpha = 0.2f,
            stroke = null
        )

        // X 표시 (대각선 두 줄)
        addPath(
            pathData = listOf(
                PathNode.MoveTo(7f, 7f),
                PathNode.LineTo(17f, 17f),
                PathNode.MoveTo(17f, 7f),
                PathNode.LineTo(7f, 17f)
            ),
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        )
    }.build()
