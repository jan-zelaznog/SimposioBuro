package com.lagn.simposioburo.fragmentsModule.descargasFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicestest.Common.Services
import com.lagn.simposioburo.OnClickAdapter
import com.lagn.simposioburo.OnClickDescargas
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentDescargasBinding
import com.lagn.simposioburo.databinding.FragmentPonentesBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponse
import com.lagn.simposioburo.domain.model.response.presentacionesResponse.PresentacionesResponse
import com.lagn.simposioburo.domain.model.response.presentacionesResponse.PresentacionesResponseItem
import com.lagn.simposioburo.fragmentsModule.descargasFragment.adapter.AdapterDescargas
import com.lagn.simposioburo.fragmentsModule.descargasFragment.model.ModelDescarga
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter.AdapterPonentes
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.ModelPonentes
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.toModelPonentes
import com.lagn.simposioburo.services.Client
import com.lagn.simposioburo.util.PreferenceHelper
import com.lagn.simposioburo.util.PreferenceHelper.get
import retrofit2.Call
import retrofit2.Response


class DescargasFragment : Fragment(), OnClickDescargas {

    private lateinit var mBinding: FragmentDescargasBinding
    private lateinit var mAdapter: AdapterDescargas
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    var client = Client()
    private lateinit var tokenS: String



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


        tokenS = PreferenceHelper.defaultPrefs(requireContext()).get("access_token","")
        Toast.makeText(requireContext(), "TOKEB: $tokenS", Toast.LENGTH_LONG).show()

        getPresentaciones(tokenS)




    }


    private fun getPresentaciones(token: String) {
        val call = client.getApiClient()?.create(Services::class.java)
        val cc = call?.getPresentaciones("Bearer $token")
        cc?.enqueue(object : retrofit2.Callback<PresentacionesResponse> {
            override fun onResponse(
                call: Call<PresentacionesResponse>,
                response: Response<PresentacionesResponse>,
            ) {
                val datos = response.body()
                mAdapter = datos?.let { AdapterDescargas(it, this@DescargasFragment) }!!

                mLinearLayoutManager = LinearLayoutManager(requireContext())

                mBinding.rvDescargas.apply {
                    setHasFixedSize(true)

                    layoutManager = mLinearLayoutManager
                    adapter = mAdapter
                }


            }

            override fun onFailure(call: Call<PresentacionesResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun onCk(ponente: PresentacionesResponseItem) {
        val uri = ponente.link
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(Intent.createChooser(intent, "Choose browser"))
    }


}