package com.lagn.simposioburo.domain.model

import android.annotation.SuppressLint

class TalleresAdapterItem {
    var groupId: Int? = null
    var titulo: String = ""
    var taller: ModelD? = null

    constructor (titulo: String) {
        this.titulo = titulo
    }

    constructor (groupId: Int, taller:ModelD) {
        this.groupId = groupId
        this.taller = taller
    }
}