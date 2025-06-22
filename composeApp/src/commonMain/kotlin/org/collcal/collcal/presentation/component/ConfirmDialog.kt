package org.collcal.collcal.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDialog(
    title: String,
    confirmText: String,
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
) {
    BasicAlertDialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(2.dp),
            shadowElevation = 5.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = { onDismissRequest() },
                        colors = ButtonDefaults.buttonColors(containerColor = mainColor)
                    ) { Text(text = Strings.cancel, color = white) }
                    Button(
                        onClick = {
                            onClick()
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = mainColor)
                    ) { Text(text = confirmText, color = white) }
                }
            }
        }
    }
}
