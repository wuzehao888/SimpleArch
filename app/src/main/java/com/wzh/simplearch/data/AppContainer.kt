package com.wzh.simplearch.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wzh.simplearch.network.AmphibianApiService
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

/**
 * create by hao
 * 2026/2/6
 */
interface AppContainer {
    val amphibianRepository: AmphibianRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:8080"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    override val amphibianRepository: AmphibianRepository by lazy {
        NetworkAmphibianRepository(
            baseUrl,
            retrofit.create(AmphibianApiService::class.java)
        )
    }
}