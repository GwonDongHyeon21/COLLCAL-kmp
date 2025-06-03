package org.collcal.collcal.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import collcal.composeapp.generated.resources.PretendardGOVVariable
import collcal.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun PretendardFontFamily() = FontFamily(
    Font(Res.font.PretendardGOVVariable, FontWeight.Normal)
)

@Composable
fun CollCalTypography() = Typography(
    displayLarge = TextStyle(fontFamily = PretendardFontFamily()),
    displayMedium = TextStyle(fontFamily = PretendardFontFamily()),
    displaySmall = TextStyle(fontFamily = PretendardFontFamily()),
    headlineLarge = TextStyle(fontFamily = PretendardFontFamily()),
    headlineMedium = TextStyle(fontFamily = PretendardFontFamily()),
    headlineSmall = TextStyle(fontFamily = PretendardFontFamily()),
    titleLarge = TextStyle(fontFamily = PretendardFontFamily()),
    titleMedium = TextStyle(fontFamily = PretendardFontFamily()),
    titleSmall = TextStyle(fontFamily = PretendardFontFamily()),
    bodyLarge = TextStyle(fontFamily = PretendardFontFamily()),
    bodyMedium = TextStyle(fontFamily = PretendardFontFamily()),
    bodySmall = TextStyle(fontFamily = PretendardFontFamily()),
    labelLarge = TextStyle(fontFamily = PretendardFontFamily()),
    labelMedium = TextStyle(fontFamily = PretendardFontFamily()),
    labelSmall = TextStyle(fontFamily = PretendardFontFamily())
)