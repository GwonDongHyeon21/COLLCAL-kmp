package org.collcal.collcal.network

object ApiConstants {
    // base url
    const val BASE_URL = "http://www.collcal.kro.kr"

    // auth
    const val SIGN_UP_PATH = "/auth/register"
    const val SIGN_IN_PATH = "/auth/login"

    // task
    const val TASK_DETAIL_PATH = "/task/detail"
    const val TASK_REGISTER_PATH = "/task/register"
    const val TASK_UPDATE_PATH = "/task/update"

    // credit
    const val SUBJECT_DETAIL_PATH = "/subject/detail"
    const val SUBJECT_REGISTER_PATH = "/subject/register"
    const val SUBJECT_UPDATE_PATH = "/subject/update"
    const val SUBJECT_DELETE_PATH = "/subject/delete"
}