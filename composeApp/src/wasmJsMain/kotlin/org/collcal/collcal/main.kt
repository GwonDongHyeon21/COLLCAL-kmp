package org.collcal.collcal

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.collcal.collcal.presentation.ui.theme.CollCalTypography

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        MaterialTheme(typography = CollCalTypography()) {
            CollCalWeb()
        }
    }
}