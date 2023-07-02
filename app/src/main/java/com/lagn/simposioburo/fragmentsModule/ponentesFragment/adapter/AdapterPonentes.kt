package com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.OnClickAdapter
import com.lagn.simposioburo.R

import com.lagn.simposioburo.databinding.ItemPonentesRvBinding

import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponseItem
import com.squareup.picasso.Picasso


class AdapterPonentes( private var datosInfo: MutableList<PonentesResponseItem>, private var listener: OnClickAdapter): RecyclerView.Adapter<AdapterPonentes.MyViewHolderPonente>() {


    private lateinit var contexto: Context

    inner class MyViewHolderPonente(view: View): RecyclerView.ViewHolder(view){

        val mBinding = ItemPonentesRvBinding.bind(view)

        fun setListener(modelPonentes: PonentesResponseItem){
            with(mBinding.root){
                setOnClickListener { listener.onCk(modelPonentes) }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPonente {

        contexto = parent.context

        val view = LayoutInflater.from(contexto).inflate(R.layout.item_ponentes_rv, parent, false)

        return MyViewHolderPonente(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderPonente, position: Int) {
        val datoInfo  = datosInfo[position]

        with(holder){

            setListener(datoInfo)
            mBinding.tvCargoPonente.text = datoInfo.puesto
            mBinding.tvNamePonenete.text = datoInfo.nombre
            Picasso.with(contexto).load(datoInfo.foto).into(mBinding.imgPonente)





        }
    }

    override fun getItemCount(): Int = datosInfo.size




}