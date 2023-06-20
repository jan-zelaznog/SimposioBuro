package com.lagn.simposioburo.registrarModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ItemRv1Binding
import com.lagn.simposioburo.domain.model.ModelD
import java.security.AccessController.getContext

class Adapter(private var datosInfo: MutableList<ModelD>): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private lateinit var mContext: Context

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

            mBinding.cvTalleres.setOnLongClickListener {

                if ( !mBinding.cvTalleres.isChecked ) {

                    mBinding.cvTalleres.isChecked = true

                    mBinding.cvTalleres.background = ContextCompat.getDrawable(mContext,R.drawable.gradiente_radius)



                }


                else {
                    mBinding.cvTalleres.isChecked = false
                    mBinding.cvTalleres.background = ContextCompat.getDrawable(mContext,R.drawable.state_cv)



                }




                true
            }

            mBinding.tvTallerHorario.text = datoInfo.tallerHorario
            mBinding.tvNombreApellido.text = datoInfo.name
            mBinding.tvCargo.text = datoInfo.cargo
            mBinding.tvDescripcion.text = datoInfo.descripcion



        }


    }

    override fun getItemCount(): Int = datosInfo.size
}