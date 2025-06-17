package org.collcal.collcal.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // 알 수 없는 키 무시
                prettyPrint = true // JSON 예쁘게 출력 (디버깅용)
                isLenient = true // 느슨한 파싱 허용
            })
        }
        // 기타 필요한 플러그인 추가 (예: Logging, Auth 등)
        // install(Logging) {
        //     logger = Logger.DEFAULT
        //     level = LogLevel.ALL
        // }
    }
}