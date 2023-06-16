package com.lagn.simposioburo.domain.model

import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.ModelPonentes

data class ModelD(
    var descripcion: String = "",
    var tallerHorario: String = "",
    var name: String = "",
    var cargo: String = "",
    var horaReceso: String = ""
)

fun ModelPonentes.toDatosPon() = ModelD(name = ponente, cargo = cargoPonente )

