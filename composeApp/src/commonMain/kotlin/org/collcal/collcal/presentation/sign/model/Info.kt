package org.collcal.collcal.presentation.sign.model

import androidx.compose.runtime.MutableState

data class Info(
    val label: String,
    val placeholder: String,
    val options: List<String>,
    var option: MutableState<String>,
    var isExpanded: MutableState<Boolean>,
)