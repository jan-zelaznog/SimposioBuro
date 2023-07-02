package com.lagn.simposioburo.domain.model.response.conferenciasResponse

data class ConferenciasResponseItem(
    val calendario: Int,
    val cant_speakers: Int,
    val created: String,
    val cupo: Any,
    val descripcion: String,
    val dia: Int,
    val end_date: String,
    val es_taller: Int,
    val estado: Int,
    val fecha: String,
    val grupo: Int,
    val hora_fin: String,
    val hora_inicio: String,
    val id: Int,
    val moderador: String,
    val modified: String,
    val mostrar: Int,
    val puesto: String,
    val salon: String,
    val speaker: String,
    val start_date: String,
    val taller: Int,
    val titulo: String,
    val url_player: String
)