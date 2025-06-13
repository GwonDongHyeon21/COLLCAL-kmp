package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.component.DownArrowIcon
import org.collcal.collcal.presentation.component.EyeIcon
import org.collcal.collcal.presentation.component.EyeOffIcon
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray18
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.transparent

@Composable
fun CustomOutlinedTextField(
    text: String,
    placeholder: String,
    numBerOption: Boolean = false,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length < 40) {
                if (numBerOption) {
                    if (it.all { value -> value.isDigit() })
                        onValueChanged(it)
                } else
                    onValueChanged(it)
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.W400,
                color = gray18
            )
        },
        keyboardOptions = if (numBerOption) KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) else KeyboardOptions.Default,
        modifier = Modifier.fillMaxWidth().border(1.dp, mainColor, RoundedCornerShape(21.dp)),
        shape = RoundedCornerShape(21.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = transparent,
            unfocusedContainerColor = transparent,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            cursorColor = mainColor
        ),
        maxLines = 1
    )
}

@Composable
fun CustomPasswordTextField(
    text: String,
    placeholder: String,
    passwordVisible: Boolean,
    onValueChanged: (String) -> Unit,
    onClickVisible: () -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { if (it.length < 40) onValueChanged(it) },
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.W400,
                color = gray18
            )
        },
        modifier = Modifier.fillMaxWidth().border(1.dp, mainColor, RoundedCornerShape(21.dp)),
        shape = RoundedCornerShape(21.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = transparent,
            unfocusedContainerColor = transparent,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            cursorColor = mainColor
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
    placeholder: String,
    onClick: () -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { },
        textStyle = TextStyle(color = black),
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.W400,
                color = gray18
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, mainColor, RoundedCornerShape(21.dp))
            .clickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = null
            ),
        shape = RoundedCornerShape(21.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = transparent,
            disabledIndicatorColor = transparent,
            cursorColor = transparent,
            disabledTextColor = transparent,
        ),
        trailingIcon = {
            Icon(
                imageVector = DownArrowIcon,
                contentDescription = "Dropdown Arrow",
                tint = mainColor
            )
        },
        maxLines = 1,
        enabled = false
    )
}