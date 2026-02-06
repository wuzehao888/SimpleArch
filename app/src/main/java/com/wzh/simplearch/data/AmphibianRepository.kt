package com.wzh.simplearch.data

import com.wzh.simplearch.network.Amphibian
import com.wzh.simplearch.network.AmphibianApiService

/**
 * create by hao
 * 2026/2/6
 */
interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibianRepository(
    private val baseUrl: String,
    private val amphibianApiService: AmphibianApiService
) :
    AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.getAmphibians().map {
            it.copy(imgSrc = "${baseUrl}${it.imgSrc}")
        }
    }
}