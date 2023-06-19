package com.lagn.simposioburo.fragmentsModule.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentDescargasBinding
import com.lagn.simposioburo.databinding.FragmentHomeBinding
import com.lagn.simposioburo.fragmentsModule.descargasFragment.adapter.AdapterDescargas


class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.cv14De

    }


}