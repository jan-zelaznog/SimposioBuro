package com.lagn.simposioburo.registrarModule

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicestest.Common.Services
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.lagn.simposioburo.OnClickAdapter
import com.lagn.simposioburo.OnClickTalleres
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ActivityRegistrarUsuarioBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.domain.model.UserModel
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponse
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponseItem
import com.lagn.simposioburo.domain.model.response.talleresResp.Talleresresp
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.adapter.AdapterPonentes
import com.lagn.simposioburo.registrarModule.adapter.Adapter
import com.lagn.simposioburo.services.Client
import retrofit2.Call
import retrofit2.Response

class RegistrarUsuario : AppCompatActivity(), OnClickTalleres {
    private lateinit var mBinding: ActivityRegistrarUsuarioBinding
    private lateinit var mAdapter: Adapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    var workshopsSelected = mutableListOf<Int>()
    var client = Client()
    override fun onSelectWorkshop(taller: ModelD) {
        Snackbar.make(mBinding.root,"${taller.titulo}", Snackbar.LENGTH_SHORT).show()
        val index = taller.id
        if (workshopsSelected.contains(index)) {
            workshopsSelected.remove(index)
        }
        else {
            workshopsSelected.add(taller.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getWorkshops()
        materialSpinner()
        mBinding.tvAviso.setOnClickListener {
            val uri = "https://www.burodecredito.com.mx/aviso-de-privacidad.html"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(Intent.createChooser(intent, "Choose browser"))
        }
        mBinding.btnRegistrarme.setOnClickListener {
            if (validateFields(
                    mBinding.tilSpinner,
                    mBinding.tilTelefono,
                    mBinding.tilEmpresa,
                    mBinding.tilConfirmaCorreo,
                    mBinding.tilCorreoElectronico,
                    mBinding.tilAMaterno,
                    mBinding.tilAPaterno,
                    mBinding.tilNombre
                )
            ) {
                /*
                val userReg = UserModel(mBinding.tilCorreoElectronico.editText.text.toString(),
                    mBinding.tilNombre.editText.text.toString(),
                    mBinding.tilAPaterno.editText.text.toString(),
                    mBinding.tilAMaterno.editText.text.toString(),
                    mBinding.tilEmpresa.editText.text.toString(),
                    "",
                    mBinding.tilTelefono.editText.text.toString(),

                    arrayOf(1, 5)
                )
 */
            }
        }
    }

    private fun getWorkshops() {
        val call = client.getApiClient()?.create(Services::class.java)
        val cc = call?.getTalleres()
        cc?.enqueue(object : retrofit2.Callback<Talleresresp> {
            override fun onResponse(
                call: Call<Talleresresp>,
                response: Response<Talleresresp>,
            ) {
                val datos = response.body()
                mAdapter = Adapter(datos?.data as MutableList<ModelD>, this@RegistrarUsuario)
                mLinearLayoutManager = LinearLayoutManager(this@RegistrarUsuario)
                mBinding.rv1.apply {
                    layoutManager = mLinearLayoutManager
                    adapter = mAdapter
                }

            }

            override fun onFailure(call: Call<Talleresresp>, t: Throwable) {
                Toast.makeText(this@RegistrarUsuario, "Error en el servidor", Toast.LENGTH_SHORT).show()

            }

        })

    }

    private fun validateFields(vararg textFields: TextInputLayout): Boolean {
        var isValid = true
        val asistencia = mBinding.etTalleres.text
        if (!asistencia.equals("No")) {
            if (workshopsSelected.count() == 0 || workshopsSelected.count() > 3) {
                Snackbar.make(
                    mBinding.root,
                    "Debe seleccionar al menos un taller y 3 como máximo",
                    Snackbar.LENGTH_SHORT
                ).show()
                return false
            }
            for (textField in textFields) {
                if (textField.editText?.text.toString().trim().isEmpty()) {
                    textField.error = getString(R.string.helper_required)
                    textField.requestFocus()
                    isValid = false
                } else textField.error = null
            }
        } else {
            for (textField in textFields) {
                if (textField.editText?.text.toString().trim().isEmpty()) {
                    textField.error = getString(R.string.helper_required)
                    textField.requestFocus()
                    isValid = false
                }
                else textField.error = null
            }
        }
        if (!isValid)
            Snackbar.make(mBinding.root, R.string.registro_no_exitoso, Snackbar.LENGTH_SHORT).show()
        else {
            val em1 = mBinding.etCorreoElec?.text.toString().trim()
            val em2 = mBinding.etConfirmaCorreo?.text.toString().trim()
            if (em1.equals(em2)) {
                return true
            } else {
                Snackbar.make(mBinding.root, "No coinciden los correos", Snackbar.LENGTH_SHORT).show()
                return false
            }
        }
        return isValid
    }



    private fun materialSpinner(){
        val respuestas = resources.getStringArray(R.array.Respuestas)
        val adapterArray= ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,respuestas)
        mBinding.etTalleres.setAdapter(adapterArray)
        mBinding.etTalleres.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0  ->{
                    //Toast.makeText(this, "click item = ${id}", Toast.LENGTH_SHORT).show()

                    mBinding.lbl1.setVisibility(View.GONE)
                    mBinding.lbl2.setVisibility(View.GONE)
                    mBinding.rv1.setVisibility(View.GONE)
                }
                1  ->{
                    //Toast.makeText(this, "click item = ${position}", Toast.LENGTH_SHORT).show()
                    mBinding.lbl1.setVisibility(View.VISIBLE)
                    mBinding.lbl2.setVisibility(View.VISIBLE)
                    mBinding.rv1.setVisibility(View.VISIBLE)
                }
            }
        }
    }
}