package org.collcal.collcal.network

import org.collcal.collcal.presentation.ui.theme.BaseUrl

object ApiConstants {
    // base url
    const val BASE_URL = BaseUrl.BASE_URL

    // auth
    const val AUTH_REGISTER_PATH = "/auth/register"
    const val AUTH_LOGIN_PATH = "/auth/login"
    const val AUTH_USER_DETAIL_PATH = "/auth/user/detail"

    // task
    const val TASK_DETAIL_PATH = "/task/detail"
    const val TASK_REGISTER_PATH = "/task/register"
    const val TASK_UPDATE_PATH = "/task/update"
    const val TASK_DELETE_PATH = "/task/delete"

    // credit
    const val SUBJECT_DETAIL_PATH = "/subject/detail"
    const val SUBJECT_REGISTER_PATH = "/subject/register"
    const val SUBJECT_UPDATE_PATH = "/subject/update"
    const val SUBJECT_DELETE_PATH = "/subject/delete"
}