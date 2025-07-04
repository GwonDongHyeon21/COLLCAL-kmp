package org.collcal.collcal.network

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import org.collcal.collcal.network.ApiConstants.AUTH_LOGIN_PATH
import org.collcal.collcal.network.ApiConstants.AUTH_REGISTER_PATH
import org.collcal.collcal.network.ApiConstants.AUTH_USER_DETAIL_PATH
import org.collcal.collcal.network.ApiConstants.BASE_URL
import org.collcal.collcal.network.ApiConstants.SUBJECT_DELETE_PATH
import org.collcal.collcal.network.ApiConstants.SUBJECT_DETAIL_PATH
import org.collcal.collcal.network.ApiConstants.SUBJECT_REGISTER_PATH
import org.collcal.collcal.network.ApiConstants.SUBJECT_UPDATE_PATH
import org.collcal.collcal.network.ApiConstants.TASK_DELETE_PATH
import org.collcal.collcal.network.ApiConstants.TASK_DETAIL_PATH
import org.collcal.collcal.network.ApiConstants.TASK_REGISTER_PATH
import org.collcal.collcal.network.ApiConstants.TASK_UPDATE_PATH
import org.collcal.collcal.network.model.AddCourseRequest
import org.collcal.collcal.network.model.GetCoursesResponse
import org.collcal.collcal.network.model.GetTaskResponse
import org.collcal.collcal.network.model.ModifyCourseRequest
import org.collcal.collcal.network.model.ResponseMessage
import org.collcal.collcal.network.model.SignInResponse
import org.collcal.collcal.platform.getToken
import org.collcal.collcal.presentation.college.model.User
import org.collcal.collcal.presentation.sign.model.SignIn
import org.collcal.collcal.presentation.sign.model.SignUp
import org.collcal.collcal.presentation.tasks.model.AddTaskRequest
import org.collcal.collcal.presentation.tasks.model.ModifyTaskRequest
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.user.model.Credit

class ApiService {

    suspend fun signUp(request: SignUp): ResponseMessage {
        return ApiClient.httpClient.post("$BASE_URL$AUTH_REGISTER_PATH") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun signIn(request: SignIn): SignInResponse {
        return ApiClient.httpClient.post("$BASE_URL$AUTH_LOGIN_PATH") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getUser(): User {
        val token = getToken()
        return ApiClient.httpClient.get("$BASE_URL$AUTH_USER_DETAIL_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }

    suspend fun getTasks(): GetTaskResponse {
        val token = getToken()
        return ApiClient.httpClient.get("$BASE_URL$TASK_DETAIL_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }

    suspend fun addTask(task: AddTaskRequest): ResponseMessage {
        val token = getToken()
        return ApiClient.httpClient.post("$BASE_URL$TASK_REGISTER_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(task)
        }.body()
    }

    suspend fun modifyTask(task: ModifyTaskRequest): ResponseMessage {
        val token = getToken()
        return ApiClient.httpClient.patch("$BASE_URL$TASK_UPDATE_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(task)
        }.body()
    }

    suspend fun deleteTask(task: Task): ResponseMessage {
        val token = getToken()
        return ApiClient.httpClient.delete("$BASE_URL$TASK_DELETE_PATH") {
            header(HttpHeaders.Authorization, "Bearer $token")
            url { parameters.append("taskId", task.taskId) }
        }.body()
    }

    suspend fun getCredits(): GetCoursesResponse {
        val token = getToken()
        return ApiClient.httpClient.get("$BASE_URL$SUBJECT_DETAIL_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }

    suspend fun addCredit(course: AddCourseRequest): ResponseMessage {
        val token = getToken()
        return ApiClient.httpClient.post("$BASE_URL$SUBJECT_REGISTER_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(course)
        }.body()
    }

    suspend fun modifyCredit(course: ModifyCourseRequest): ResponseMessage {
        val token = getToken()
        return ApiClient.httpClient.patch("$BASE_URL$SUBJECT_UPDATE_PATH") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(course)
        }.body()
    }

    suspend fun deleteCredit(credit: Credit): ResponseMessage {
        val token = getToken()
        return ApiClient.httpClient.delete("$BASE_URL$SUBJECT_DELETE_PATH") {
            header(HttpHeaders.Authorization, "Bearer $token")
            url { parameters.append("subjectId", credit.creditId) }
        }.body()
    }
}