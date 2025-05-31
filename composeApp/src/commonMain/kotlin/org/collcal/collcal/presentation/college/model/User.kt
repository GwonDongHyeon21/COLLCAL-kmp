package org.collcal.collcal.presentation.college.model

data class User(
    val name: String,
    val department: String,
    val semester: String,
    val semesterInt: Int,
    val earnedCredits: Int,
    val averageCredit: Double,
    val userMajorRequiredCredits: Int,
    val majorRequiredCredits: Int,
    val userMajorElectiveCredits: Int,
    val majorElectiveCredits: Int,
    val userRequiredLiberalArtsCredits: Int,
    val requiredLiberalArtsCredits: Int,
    val userDistributedCredits: Int,
    val distributedCredits: Int,
)