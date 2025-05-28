package org.collcal.collcal.component

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDateFormatted(): String {
    val now = Clock.System.now()
    val dateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
    val year = dateTime.date.year
    val month = dateTime.date.monthNumber.toString().padStart(2, '0')
    val day = dateTime.date.dayOfMonth.toString().padStart(2, '0')

    return "$year.$month.$day"  // 예: 2025.05.25
}