package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.component.DownArrowIcon
import org.collcal.collcal.presentation.component.EyeIcon
import org.collcal.collcal.presentation.component.EyeOffIcon
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
fun CustomPasswordTextField(
    text: String,
    passwordVisible: Boolean,
    onValueChanged: (String) -> Unit,
    onClickVisible: () -> Unit,
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
        maxLines = 1,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                imageVector = if (passwordVisible) EyeIcon else EyeOffIcon,
                contentDescription = "EyeIcon",
                modifier = Modifier.clickable(
                    onClick = { onClickVisible() },
                    indication = null,
                    interactionSource = null
                )
            )
        },
    )
}

@Composable
fun CustomDropDownTextField(
    text: String,
    onClick: () -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { },
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
        trailingIcon = { Icon(imageVector = DownArrowIcon, contentDescription = "Dropdown Arrow") },
        maxLines = 1,
        enabled = false
    )
}