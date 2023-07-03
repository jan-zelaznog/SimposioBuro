package com.lagn.simposioburo.fragmentsModule.descargasFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.OnClickAdapter
import com.lagn.simposioburo.OnClickDescargas
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ItemDescargasRvBinding
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponseItem
import com.lagn.simposioburo.domain.model.response.presentacionesResponse.PresentacionesResponse
import com.lagn.simposioburo.domain.model.response.presentacionesResponse.PresentacionesResponseItem

import com.lagn.simposioburo.fragmentsModule.descargasFragment.model.ModelDescarga

class AdapterDescargas(private var datosInfo: MutableList<PresentacionesResponseItem>, private var listener: OnClickDescargas) : RecyclerView.Adapter<AdapterDescargas.MyViewHolderDescarga>() {


    private lateinit var contexto: Context

    inner class MyViewHolderDescarga(view: View): RecyclerView.ViewHolder(view){

        val mBinding = ItemDescargasRvBinding.bind(view)
        fun setListener(modelDescargas: PresentacionesResponseItem){
            with(mBinding.root){
                setOnClickListener { listener.onCk(modelDescargas) }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDescarga {

        contexto = parent.context

        val view = LayoutInflater.from(contexto).inflate(R.layout.item_descargas_rv, parent, false)

        return MyViewHolderDescarga(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderDescarga, position: Int) {
        val datoInfo  = datosInfo.get(position)

        with(holder){
            setListener(datoInfo)
            mBinding.tvDescarga.text = datoInfo.titulo



        }
    }

    override fun getItemCount(): Int = datosInfo.size

}