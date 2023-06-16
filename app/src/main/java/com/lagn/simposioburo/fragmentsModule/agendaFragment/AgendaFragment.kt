package com.lagn.simposioburo.fragmentsModule.agendaFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentAgendaBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.fragmentsModule.agendaFragment.adapter.AdapterAgenda
import com.lagn.simposioburo.registrarModule.adapter.Adapter


class AgendaFragment : Fragment() {
    private lateinit var mBinding: FragmentAgendaBinding
    private lateinit var mAdapter: AdapterAgenda
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //
        mBinding = FragmentAgendaBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.cv14De.setOnClickListener {         setupRecylcerView()
        }
        mBinding.cv13DeJulio.setOnClickListener {         setupRecylcerView()
        }


    }


    private fun setupRecylcerView() {
        val datos = listOf<ModelD>(
            ModelD(name = "Alejandro Corés Luque",
                descripcion = "Taller 1 - Retando la gestion de la cobranza actual",
                cargo = "GERENTE COMERCIAL SECTOR FINANCIERO", tallerHorario = "16:00\n16:45"
            ),
            ModelD(name = "Gabriela Jaber Tejeda",
                descripcion = "Taller 2 -e-Visor fianciero facilitando el analisis digital",
                cargo = "cargo del ponente",

            ),

            ModelD(name = "Rolando Schultz Sepeda",
                descripcion = " Taller 3- prevencion de fraudes en el ciclo de crédito",
                cargo = "GERENTE DE PREVENCION DE FRAUDE",
                 ),

            ModelD(
                descripcion = "Receso",
                tallerHorario = "16:45\n17:00"
            ),
            ModelD(name = "Alejandro Corés Luque",
                descripcion = "Taller 1 - Retando la gestion de la cobranza actual",
                cargo = "GERENTE COMERCIAL SECTOR FINANCIERO", tallerHorario = "16:00\n16:45"
            ),
            ModelD(name = "Gabriela Jaber Tejeda",
                descripcion = "Taller 2 -e-Visor fianciero facilitando el analisis digital",
                cargo = "cargo del ponente",

                ),

            ModelD(name = "Rolando Schultz Sepeda",
                descripcion = " Taller 3- prevencion de fraudes en el ciclo de crédito",
                cargo = "GERENTE DE PREVENCION DE FRAUDE",
            ),

            ModelD(
                descripcion = "Receso",
                tallerHorario = "16:45\n17:00"
            )


        )
        mAdapter = AdapterAgenda(datos as MutableList<ModelD>)

        mLinearLayoutManager = LinearLayoutManager(requireContext())

        mBinding.rv2.apply {
            setHasFixedSize(true)

            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }



}