package org.collcal.collcal.network

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.collcal.collcal.network.ApiConstants.BASE_URL
import org.collcal.collcal.network.ApiConstants.SIGN_IN_PATH
import org.collcal.collcal.network.ApiConstants.SIGN_UP_PATH
import org.collcal.collcal.network.model.ResponseMessage
import org.collcal.collcal.network.model.SignInResponse
import org.collcal.collcal.presentation.sign.model.SignIn
import org.collcal.collcal.presentation.sign.model.SignUp

class ApiService {

    private val token = getToken()

    suspend fun signUp(request: SignUp): ResponseMessage {
        return ApiClient.httpClient.post("$BASE_URL$SIGN_UP_PATH") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun signIn(request: SignIn): SignInResponse {
        return ApiClient.httpClient.post("$BASE_URL$SIGN_IN_PATH") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}