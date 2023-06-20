package com.lagn.simposioburo.fragmentsModule.agendaFragment.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ItemAgendaRvBinding
import com.lagn.simposioburo.domain.model.ModelD


class AdapterAgenda(private var datosInfo: MutableList<ModelD>): RecyclerView.Adapter<AdapterAgenda.MyViewHolderAgenda>() {

    private lateinit var contexto:Context

    inner class MyViewHolderAgenda(view: View): RecyclerView.ViewHolder(view){

        val mBinding = ItemAgendaRvBinding.bind(view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderAgenda {

        contexto = parent.context

        val view = LayoutInflater.from(contexto).inflate(R.layout.item_agenda_rv, parent, false)

        return MyViewHolderAgenda(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderAgenda, position: Int) {
        val datoInfo  = datosInfo.get(position)

        with(holder){
            mBinding.tvCargo.text = datoInfo.cargo
            mBinding.tvDescripcion.text =  datoInfo.descripcion
            mBinding.tvNombreApellido.text = datoInfo.name

            if (datoInfo.descripcion == "Receso"){
                mBinding.tvDescripcion.setBackgroundResource(R.drawable.gradiente)
                mBinding.tvDescripcion.setTextColor(ContextCompat.getColor(contexto,R.color.white))

            }
            if (datoInfo.tallerHorario.isEmpty()){
                mBinding.tvHorario.setText(R.string.tv_horario_empty)
                mBinding.tvHorario.setTextColor(ContextCompat.getColor(contexto,R.color.white))


            }else mBinding.tvHorario.text = datoInfo.tallerHorario



        }
    }

    override fun getItemCount(): Int = datosInfo.size


}