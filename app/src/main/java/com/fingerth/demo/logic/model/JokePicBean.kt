package com.fingerth.demo.logic.model

data class JokePicBean(
    val `data`: List<JokePicData>,
    val status: JokePicStatus
)

data class JokePicData(
    val content: String,
    val hashId: String,
    val unixtime: Int,
    val updatetime: String,
    val url: String
)

data class JokePicStatus(
    val code: Int,
    val message: String
)