package com.lagn.simposioburo.fragmentsModule.ponenteDatosFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentPonenteDatosBinding
import com.lagn.simposioburo.databinding.FragmentPonentesBinding
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponseItem
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.PonentesFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.callbackFlow


class PonenteDatosFragment : Fragment() {
    private lateinit var mBinding: FragmentPonenteDatosBinding
    lateinit var tvCargoPonente : TextView
    lateinit var tvNombrePonente : TextView
    lateinit var tvCvPonente : TextView
    lateinit var imgPonente : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPonenteDatosBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args=requireArguments()
        tvCargoPonente = view.findViewById(R.id.tv_cargo_ponente)
        tvCargoPonente.text = args.getString("job")
        tvNombrePonente = view.findViewById(R.id.tv_nombre_ponente)
        tvNombrePonente.text = args.getString("name")
        tvCvPonente = view.findViewById(R.id.tv_cv_ponente)
        tvCvPonente.text = args.getString("bio")
        imgPonente = view.findViewById(R.id.img_ponente)
        val img = args.getString("img")
        Picasso.with(activity).load(img).into(imgPonente)
    }




}