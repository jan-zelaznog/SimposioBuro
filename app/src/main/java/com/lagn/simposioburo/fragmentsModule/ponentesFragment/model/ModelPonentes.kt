package com.lagn.simposioburo.fragmentsModule.ponentesFragment.model

import com.lagn.simposioburo.domain.model.ModelD

data class ModelPonentes(
    var image: String = "",
    var ponente: String,
    var cargoPonente: String,
)

fun ModelD.toModelPonentes() = ModelPonentes(ponente = name, cargoPonente = cargo)
