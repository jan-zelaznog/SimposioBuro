package com.lagn.simposioburo.domain.model.response.talleresResp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lagn.simposioburo.OnClickTalleres
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ItemTalleresBinding
import com.lagn.simposioburo.domain.model.TalleresAdapterItem

class AdapterTalleres(private var datosInfo: MutableList<TalleresAdapterItem>, delegate: OnClickTalleres): RecyclerView.Adapter<AdapterTalleres.ViewHolder>() {
    private lateinit var mContext: Context
        private var mDelegate = delegate
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val mBinding = ItemTalleresBinding.bind(view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            mContext = parent.context
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_talleres, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val datoInfo = datosInfo.get(position)
            with(holder) {
                if (datoInfo.taller == null) {
                    mBinding.tvTallerHorario.visibility = View.VISIBLE
                    mBinding.tvTallerHorario.text = datoInfo.titulo
                    mBinding.cvTalleres.visibility = View.GONE
                } else {
                    mBinding.tvTallerHorario.visibility = View.GONE
                    mBinding.cvTalleres.visibility = View.VISIBLE
                    mBinding.cvTalleres.setOnClickListener {
                        if (mDelegate.onSelectWorkshop(datoInfo, position)) {
                            if (!mBinding.cvTalleres.isChecked) {
                                mBinding.cvTalleres.isChecked = true
                                mBinding.cvTalleres.background =
                                    ContextCompat.getDrawable(mContext, R.drawable.gradiente_radius)
                            } else {
                                mBinding.cvTalleres.isChecked = false
                                mBinding.cvTalleres.background =
                                    ContextCompat.getDrawable(mContext, R.drawable.state_cv)
                            }
                            true
                        }
                    }
                    mBinding.tvNombreApellido.text = datoInfo.taller!!.speaker
                    mBinding.tvCargo.text = datoInfo.taller!!.puesto
                    mBinding.tvDescripcion.text = datoInfo.taller!!.titulo
                    mBinding.cvTalleres.background =
                        ContextCompat.getDrawable(mContext, R.drawable.state_cv)
                }
            }
        }

    override fun getItemCount(): Int = datosInfo.size

}