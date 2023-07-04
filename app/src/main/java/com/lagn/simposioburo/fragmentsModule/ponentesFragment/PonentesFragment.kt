package com.lagn.simposioburo.fragmentsModule.ponentesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicestest.Common.Services
import com.lagn.simposioburo.OnClickAdapter
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentPonentesBinding
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponse
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponseItem
import com.lagn.simposioburo.fragmentsModule.ponenteDatosFragment.PonenteDatosFragment
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter.AdapterPonentes
import com.lagn.simposioburo.services.Client
import com.lagn.simposioburo.util.PreferenceHelper
import com.lagn.simposioburo.util.PreferenceHelper.get
import retrofit2.Call
import retrofit2.Response

class PonentesFragment : Fragment(), OnClickAdapter {

    private lateinit var mBinding: FragmentPonentesBinding
    private lateinit var mAdapter: AdapterPonentes
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    var client = Client()
    private lateinit var tokenS: String


    private lateinit var mActiveFragment: Fragment

    private lateinit var mFragmentManager: FragmentManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentPonentesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenS = PreferenceHelper.defaultPrefs(requireContext()).get("access_token","")
        //Toast.makeText(requireContext(), "TOKEB: $tokenS", Toast.LENGTH_LONG).show()

        getConferencias(tokenS)


    }


    private fun getConferencias(token: String) {
        val call = client.getApiClient()?.create(Services::class.java)
        val cc = call?.getPonentes("Bearer $token")
        cc?.enqueue(object : retrofit2.Callback<PonentesResponse> {
            override fun onResponse(
                call: Call<PonentesResponse>,
                response: Response<PonentesResponse>,
            ) {
                val datos = response.body()
                mAdapter = datos?.let { AdapterPonentes(it, this@PonentesFragment) }!!

                mLinearLayoutManager = LinearLayoutManager(requireContext())

                mBinding.rvPonentes.apply {
                    setHasFixedSize(true)

                    layoutManager = mLinearLayoutManager
                    adapter = mAdapter
                }

            }

            override fun onFailure(call: Call<PonentesResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_SHORT).show()

            }

        })

    }



    override fun onCk(ponente: PonentesResponseItem) {
        launchEditFragment(ponente)

    }


    private fun launchEditFragment(ponente: PonentesResponseItem) {
        val fragmet = PonenteDatosFragment()
        val args = Bundle()
        args.putString("name", ponente.nombre)
        args.putString("job", ponente.puesto)
        args.putString("bio", ponente.biografia)
        args.putString("img", ponente.foto)
        fragmet.arguments = args
        val fragmentManager = this@PonentesFragment.parentFragmentManager

        val fragmentTrnsatcion = fragmentManager.beginTransaction()

        fragmentTrnsatcion.add(R.id.hostFragment, fragmet)
        /*No detener la app  al dar atras */
        fragmentTrnsatcion.commit()
        fragmentTrnsatcion.addToBackStack(null)


    }


}