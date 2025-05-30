package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.transparent

@Composable
fun CustomOutlinedTextField(
    text: String,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(21.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = gray1,
            unfocusedContainerColor = gray1,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            cursorColor = black
        ),
        maxLines = 1
    )
}

@Composable
fun CustomDropDownTextField(
    text: String,
    placeholder: String,
    onClick: () -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { },
        placeholder = {
            Row {
                Text(text = placeholder, fontSize = 12.sp)
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = DownArrowIcon,
                    contentDescription = "Dropdown Arrow",
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = null
            ),
        shape = RoundedCornerShape(21.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = gray1,
            disabledIndicatorColor = transparent,
            cursorColor = black,
            disabledTextColor = black
        ),
        maxLines = 1,
        enabled = false
    )
}

val DownArrowIcon = ImageVector.Builder(
    name = "DownArrow",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Gray),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 10f)
        lineTo(12f, 15f)
        lineTo(17f, 10f)
        close()
    }
}.build()