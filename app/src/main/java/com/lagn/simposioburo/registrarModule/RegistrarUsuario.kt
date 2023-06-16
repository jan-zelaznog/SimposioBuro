package com.lagn.simposioburo.registrarModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ActivityRegistrarUsuarioBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.registrarModule.adapter.Adapter

class RegistrarUsuario : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegistrarUsuarioBinding
    private lateinit var mAdapter: Adapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //mBinding.etTalleres.text

        setupRecylcerView()
        materialSpinner()


    }

    private fun materialSpinner(){
        val respuestas = resources.getStringArray(R.array.Respuestas)
        val adapterArray= ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,respuestas)

        mBinding.etTalleres.setAdapter(adapterArray)
        mBinding.etTalleres.setOnItemClickListener { parent, view, position, id ->

            when(position){
                0  ->{
                    Toast.makeText(this, "click item = ${position}", Toast.LENGTH_SHORT).show()


                }
                1  ->{
                    Toast.makeText(this, "click item = ${position}", Toast.LENGTH_SHORT).show()


                }


            }


        }

    }


    private fun setupRecylcerView() {
        val datos = listOf<ModelD>(
            ModelD(name = "Roberto Garcia", tallerHorario = "Taller en Horario 00:00 a 00:00",
                descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod ",
                cargo = "cargo del ponente"
            ),
            ModelD(name = "Roberto Garcia", tallerHorario = "Taller en Horario 00:00 a 00:00",
                descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod ",
                cargo = "cargo del ponente"
            ),

            ModelD(name = "Roberto Garcia", tallerHorario = "Taller en Horario 00:00 a 00:00",
                descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod ",
                cargo = "cargo del ponente"
            )


        )
        mAdapter = Adapter(datos as MutableList<ModelD>)

        mLinearLayoutManager = LinearLayoutManager(this)

        mBinding.rv1.apply {

            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }

}