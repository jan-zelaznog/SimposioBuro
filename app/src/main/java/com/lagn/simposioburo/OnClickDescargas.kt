package com.lagn.simposioburo

import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponseItem
import com.lagn.simposioburo.domain.model.response.presentacionesResponse.PresentacionesResponseItem

interface OnClickDescargas {

    fun onCk(ponente: PresentacionesResponseItem)

}