package org.collcal.collcal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.collcal.collcal.presentation.ui.theme.CollCalColorScheme
import org.collcal.collcal.presentation.ui.theme.CollCalTypography

var token by mutableStateOf("")

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        MaterialTheme(
            colorScheme = CollCalColorScheme(),
            typography = CollCalTypography()
        ) {
            CollCalWeb()
        }
    }
}