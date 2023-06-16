package com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.R

import com.lagn.simposioburo.databinding.ItemPonentesRvBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.ModelPonentes


class AdapterPonentes( private var datosInfo: MutableList<ModelPonentes>): RecyclerView.Adapter<AdapterPonentes.MyViewHolderPonente>() {


    private lateinit var contexto: Context

    inner class MyViewHolderPonente(view: View): RecyclerView.ViewHolder(view){

        val mBinding = ItemPonentesRvBinding.bind(view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPonente {

        contexto = parent.context

        val view = LayoutInflater.from(contexto).inflate(R.layout.item_ponentes_rv, parent, false)

        return MyViewHolderPonente(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderPonente, position: Int) {
        val datoInfo  = datosInfo[position]

        with(holder){
            mBinding.tvCargoPonente.text = datoInfo.cargoPonente
            mBinding.tvNamePonenete.text = datoInfo.ponente




        }
    }

    override fun getItemCount(): Int = datosInfo.size




}