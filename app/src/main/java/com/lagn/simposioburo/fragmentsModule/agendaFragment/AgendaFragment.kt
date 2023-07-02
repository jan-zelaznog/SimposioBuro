package com.lagn.simposioburo.fragmentsModule.agendaFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicestest.Common.Services
import com.lagn.simposioburo.databinding.FragmentAgendaBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.domain.model.response.conferenciasResponse.ConferenciasResponse
import com.lagn.simposioburo.domain.model.response.talleresResp.Data
import com.lagn.simposioburo.domain.model.response.talleresResp.Talleresresp
import com.lagn.simposioburo.fragmentsModule.agendaFragment.adapter.AdapterAgenda
import com.lagn.simposioburo.fragmentsModule.agendaFragment.adapter.AdapterConferencias
import com.lagn.simposioburo.services.Client
import com.lagn.simposioburo.util.PreferenceHelper
import com.lagn.simposioburo.util.PreferenceHelper.get
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class AgendaFragment : Fragment() {
    private lateinit var mBinding: FragmentAgendaBinding
    private lateinit var mAdapter: AdapterAgenda
    private lateinit var mAdapterConfe: AdapterConferencias
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    var client = Client()
    private var tokenS: String = ""

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

        tokenS = PreferenceHelper.defaultPrefs(requireContext()).get("access_token","")
        Toast.makeText(requireContext(), "TOKEB: $tokenS", Toast.LENGTH_LONG).show()





        mBinding.cv14De.setOnClickListener {
            mBinding.rv2.isVisible = false
            mBinding.rvconfe.isVisible = true
            getConferencias(tokenS)

        }
        mBinding.cv13DeJulio.setOnClickListener {
            mBinding.rv2.isVisible = true
            mBinding.rvconfe.isVisible = false
            getTalleres()
        }


    }



    private fun getConferencias(token: String){
        val call = client.getApiClient()?.create(Services::class.java)
        val cc= call?.getConferencias("Bearer $token")
        cc?.enqueue(object : retrofit2.Callback<ConferenciasResponse>{
            override fun onResponse(call: Call<ConferenciasResponse>, response: Response<ConferenciasResponse>) {
                val datos = response.body()
                mAdapterConfe = datos?.let { AdapterConferencias(it) }!!

                mLinearLayoutManager = LinearLayoutManager(requireContext())

                mBinding.rvconfe.apply {
                    setHasFixedSize(true)

                    layoutManager = mLinearLayoutManager
                    adapter = mAdapterConfe
                }

            }
            override fun onFailure(call: Call<ConferenciasResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_SHORT).show()

            }

        })

    }
    private fun getTalleres(){
        val call = client.getApiClient()?.create(Services::class.java)
        val cc= call?.getTalleres()
        cc?.enqueue(object : retrofit2.Callback<Talleresresp>{
            override fun onResponse(call: Call<Talleresresp>, response: Response<Talleresresp>) {
                Log.i("resp", response.body().toString())
                val datos = response.body()?.data
                mAdapter = AdapterAgenda(datos as MutableList<Data>)

                mLinearLayoutManager = LinearLayoutManager(requireContext())

                mBinding.rv2.apply {
                    setHasFixedSize(true)

                    layoutManager = mLinearLayoutManager
                    adapter = mAdapter
                }

            }

            override fun onFailure(call: Call<Talleresresp>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_SHORT).show()

            }

        })

    }



}