package com.lagn.simposioburo.fragmentsModule.ponentesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentAgendaBinding
import com.lagn.simposioburo.databinding.FragmentPonentesBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.fragmentsModule.agendaFragment.adapter.AdapterAgenda
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter.AdapterPonentes
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.ModelPonentes
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.toModelPonentes

class PonentesFragment : Fragment() {

    private lateinit var mBinding: FragmentPonentesBinding
    private lateinit var mAdapter: AdapterPonentes
    private lateinit var mLinearLayoutManager: LinearLayoutManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentPonentesBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecylcerView()




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
        mAdapter = AdapterPonentes((datos.map {
            it.toModelPonentes()
        } as MutableList<ModelPonentes>))

        mLinearLayoutManager = LinearLayoutManager(requireContext())

        mBinding.rvPonentes.apply {
            setHasFixedSize(true)

            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }






}