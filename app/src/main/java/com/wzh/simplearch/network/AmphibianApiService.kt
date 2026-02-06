package com.wzh.simplearch.network

import retrofit2.http.GET

/**
 * create by hao
 * 2026/2/6
 */
interface AmphibianApiService {

    @GET("/photos/details")
    suspend fun getAmphibians(): List<Amphibian>
}