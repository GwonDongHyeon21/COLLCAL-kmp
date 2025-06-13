package org.collcal.collcal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import org.collcal.collcal.presentation.ui.theme.CollCalColorScheme
import org.collcal.collcal.presentation.ui.theme.CollCalTypography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = CollCalColorScheme(),
                typography = CollCalTypography()
            ) {
                CollCalApp()
            }
        }
    }
}