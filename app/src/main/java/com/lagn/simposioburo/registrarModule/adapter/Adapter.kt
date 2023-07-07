package com.lagn.simposioburo.registrarModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.OnClickTalleres
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ItemRv1Binding
import com.lagn.simposioburo.domain.model.ModelD
import java.security.AccessController.getContext

class Adapter(private var datosInfo: MutableList<ModelD>, delegate: OnClickTalleres): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private lateinit var mContext: Context
    private var mDelegate = delegate
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val mBinding = ItemRv1Binding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_rv1, parent, false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val datoInfo  = datosInfo.get(position)



        with(holder){

            mBinding.cvTalleres.setOnClickListener {
                if ( !mBinding.cvTalleres.isChecked ) {
                    mBinding.cvTalleres.isChecked = true
                    mBinding.cvTalleres.background = ContextCompat.getDrawable(mContext,R.drawable.gradiente_radius)
                }
                else {
                    mBinding.cvTalleres.isChecked = false
                    mBinding.cvTalleres.background = ContextCompat.getDrawable(mContext,R.drawable.state_cv)
                }
                //mDelegate.onSelectWorkshop(datoInfo)
                true
            }

            mBinding.tvTallerHorario.text = "Taller en horario " + datoInfo.hora_inicio?.dropLast(3) + " " + datoInfo.hora_fin?.dropLast(3)
            mBinding.tvNombreApellido.text = datoInfo.speaker
            mBinding.tvCargo.text = datoInfo.puesto
            mBinding.tvDescripcion.text = datoInfo.titulo
            mBinding.cvTalleres.background = ContextCompat.getDrawable(mContext,R.drawable.state_cv)

        }


    }

    override fun getItemCount(): Int = datosInfo.size
}