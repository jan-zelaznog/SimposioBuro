package com.lagn.simposioburo

import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.domain.model.TalleresAdapterItem

interface OnClickTalleres {

    fun onSelectWorkshop(item: TalleresAdapterItem, position:Int):Boolean



}