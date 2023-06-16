package com.lagn.simposioburo.fragmentsModule.descargasFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ItemDescargasRvBinding

import com.lagn.simposioburo.fragmentsModule.descargasFragment.model.ModelDescarga

class AdapterDescargas(private var datosInfo: MutableList<ModelDescarga>) : RecyclerView.Adapter<AdapterDescargas.MyViewHolderDescarga>() {


    private lateinit var contexto: Context

    inner class MyViewHolderDescarga(view: View): RecyclerView.ViewHolder(view){

        val mBinding = ItemDescargasRvBinding.bind(view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDescarga {

        contexto = parent.context

        val view = LayoutInflater.from(contexto).inflate(R.layout.item_descargas_rv, parent, false)

        return MyViewHolderDescarga(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderDescarga, position: Int) {
        val datoInfo  = datosInfo.get(position)

        with(holder){
            mBinding.tvDescarga.text = datoInfo.descargaInfo





        }
    }

    override fun getItemCount(): Int = datosInfo.size

}