package com.lagn.simposioburo.fragmentsModule.ponenteDatosFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentPonenteDatosBinding
import com.lagn.simposioburo.databinding.FragmentPonentesBinding
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.PonentesFragment
import kotlinx.coroutines.flow.callbackFlow


class PonenteDatosFragment : Fragment() {

    private lateinit var mbinding: FragmentPonenteDatosBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mbinding = FragmentPonenteDatosBinding.inflate(inflater,container,false)
        return mbinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }





}