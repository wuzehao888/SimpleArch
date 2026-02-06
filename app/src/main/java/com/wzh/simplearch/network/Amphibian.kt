package com.wzh.simplearch.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * create by hao
 * 2026/2/6
 */

@Serializable
data class Amphibian(
    val name: String,
    val type: String,
    @SerialName("description")
    val desc: String,
    @SerialName("img_src")
    val imgSrc: String
)