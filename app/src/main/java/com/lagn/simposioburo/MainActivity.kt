package com.lagn.simposioburo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lagn.simposioburo.databinding.ActivityMainBinding
import com.lagn.simposioburo.fragmentsModule.HomeActivity
import com.lagn.simposioburo.registrarModule.RegistrarUsuario

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)




        mBinding.tvRegistrarse.setOnClickListener {
            startActivity(Intent(this, RegistrarUsuario::class.java))
            finish()
        }
        mBinding.btnIngresar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))

        }
    }









}