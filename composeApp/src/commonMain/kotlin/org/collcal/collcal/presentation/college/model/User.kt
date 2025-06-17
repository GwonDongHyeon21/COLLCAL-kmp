package org.collcal.collcal.presentation.college.model

data class User(
    val name: String,
    val department: String,
    val semester: String,
    val semesterInt: Int,
    val majorBasicCredits: Int,
    val majorRequiredCredits: Int,
    val majorElectiveCredits: Int,
    val requiredLiberalArtsCredits: Int,
    val distributedCredits: Int,
    val freeComplementCredits: Int
)