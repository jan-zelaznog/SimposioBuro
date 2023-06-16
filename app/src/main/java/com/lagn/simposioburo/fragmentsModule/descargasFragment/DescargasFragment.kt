package com.lagn.simposioburo.fragmentsModule.descargasFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentDescargasBinding
import com.lagn.simposioburo.databinding.FragmentPonentesBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.fragmentsModule.descargasFragment.adapter.AdapterDescargas
import com.lagn.simposioburo.fragmentsModule.descargasFragment.model.ModelDescarga
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter.AdapterPonentes
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.ModelPonentes
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.toModelPonentes


class DescargasFragment : Fragment() {

    private lateinit var mBinding: FragmentDescargasBinding
    private lateinit var mAdapter: AdapterDescargas
    private lateinit var mLinearLayoutManager: LinearLayoutManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentDescargasBinding.inflate(inflater,container,false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecylcerView()




    }

    private fun setupRecylcerView() {
        val datos = listOf<ModelDescarga>(
            ModelDescarga(descargaInfo = "Retando la gestión de cobranza actual."
            ),
            ModelDescarga(descargaInfo = "e-Visor financiero facilitando el análisis digtial."
            ),

            ModelDescarga(descargaInfo = "Cómo Originar con clientes sin historial créditicio."
            ),



        )
        mAdapter = AdapterDescargas((datos as MutableList<ModelDescarga>))

        mLinearLayoutManager = LinearLayoutManager(requireContext())

        mBinding.rvDescargas.apply {
            setHasFixedSize(true)

            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }


}