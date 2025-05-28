package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.gray6

@Composable
fun CustomDropDown(
    options: List<String>,
    isExpanded: Boolean,
    onClickExpanded: () -> Unit,
    onClickOption: (String) -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onClickExpanded() },
        containerColor = gray6
    ) {
        Column(modifier = Modifier.heightIn(max = 200.dp).verticalScroll(rememberScrollState())) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onClickOption(option)
                        onClickExpanded()
                    },
                    text = { Text(text = option) },
                )
            }
        }
    }
}