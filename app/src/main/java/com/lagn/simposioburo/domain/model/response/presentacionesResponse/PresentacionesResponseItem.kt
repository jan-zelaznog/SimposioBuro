package com.lagn.simposioburo.domain.model.response.presentacionesResponse

data class PresentacionesResponseItem(
    val created: String,
    val dia: Int,
    val estado: Int,
    val id: Int,
    val link: String,
    val modified: String,
    val ponente: String,
    val puesto: String,
    val tipo: String,
    val titulo: String
)