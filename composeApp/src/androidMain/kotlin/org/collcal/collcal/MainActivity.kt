package org.collcal.collcal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import org.collcal.collcal.component.Token.getUserToken
import org.collcal.collcal.presentation.ui.theme.CollCalColorScheme
import org.collcal.collcal.presentation.ui.theme.CollCalTypography

class MainActivity : ComponentActivity() {

    @SuppressLint("StaticFieldLeak")
    companion object {
        lateinit var context: Context
        lateinit var token: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        context = applicationContext
        token = getUserToken(context)

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