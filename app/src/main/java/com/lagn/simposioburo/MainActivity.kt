package com.lagn.simposioburo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.servicestest.Common.Services
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lagn.simposioburo.databinding.ActivityMainBinding
import com.lagn.simposioburo.domain.model.LoginModel
import com.lagn.simposioburo.domain.model.response.loginResponse.LoginResponse
import com.lagn.simposioburo.fragmentsModule.HomeActivity
import com.lagn.simposioburo.registrarModule.RegistrarUsuario
import com.lagn.simposioburo.services.Client
import com.lagn.simposioburo.util.PreferenceHelper
import com.lagn.simposioburo.util.PreferenceHelper.get
import com.lagn.simposioburo.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding


    private var client = Client()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["access_token",""].isNotEmpty()){
            goToMain()

        }




        mBinding.tvRegistrarse.setOnClickListener {
            startActivity(Intent(this, RegistrarUsuario::class.java))
            finish()
        }
        mBinding.btnIngresar.setOnClickListener {
            login()
            //goToMain()


        }
    }

    private fun login(){
        val mail = mBinding.etCorreo.text.toString()
        if (!mail.equals("")) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                val call = client.getApiClient()?.create(Services::class.java)
                val cc = call?.loginService(LoginModel(mail))
                cc?.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.i("resp", response.body().toString())
                        //startActivity(Intent(mBinding.etCorreo.context, HomeActivity::class.java))

                        if (response.isSuccessful) {
                            val loginRespose = response.body()

                            if (loginRespose == null) {
                                Toast.makeText(
                                    mBinding.etCorreo.context,
                                    "Error en el servidor",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return

                            }
                            if (loginRespose.status == "success") {
                                Toast.makeText(
                                    mBinding.etCorreo.context,
                                    "Acceso exitoso",
                                    Toast.LENGTH_SHORT
                                ).show()

                                createSesionPref(loginRespose.access_token)
                                goToMain()

                            } else {
                                Toast.makeText(
                                    mBinding.etCorreo.context,
                                    "Las credenciales son incorrectas",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        } else {
                            MaterialAlertDialogBuilder(this@MainActivity)
                                .setTitle("Correo electrónico no permitido o no ha completado el registro ")
                                .setPositiveButton(R.string.dialog_ok) { _, _ ->
                                }
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                        Toast.makeText(
                            mBinding.etCorreo.context,
                            "Error en el servidor",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                })
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Ingrese un correo válido")
                    .setPositiveButton(R.string.dialog_ok) { _, _ ->
                    }
                    .show()
            }
        }
        else {
            MaterialAlertDialogBuilder(this)
                .setTitle("Su correo es requerido")
                .setPositiveButton(R.string.dialog_ok) { _, _ ->
                }
                .show()
        }
    }


    private fun goToMain(){
        startActivity(Intent(mBinding.etCorreo.context, HomeActivity::class.java))
        finish()


    }

    private fun createSesionPref(token: String){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["access_token"]= token
    }










}