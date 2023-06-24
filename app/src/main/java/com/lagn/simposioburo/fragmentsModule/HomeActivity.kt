package com.lagn.simposioburo.fragmentsModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lagn.simposioburo.MainActivity
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ActivityHomeBinding
import com.lagn.simposioburo.fragmentsModule.agendaFragment.AgendaFragment
import com.lagn.simposioburo.fragmentsModule.descargasFragment.DescargasFragment
import com.lagn.simposioburo.fragmentsModule.homeFragment.HomeFragment
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.PonentesFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHomeBinding

    private lateinit var mActiveFragment: Fragment

    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNav()
        logOut()



    }


    private fun logOut(){
        mBinding.cerrarSesion.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_cerrar_sesion)
                .setPositiveButton(R.string.dialog_salir_confirm) { _, _ ->
                   startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                .setNegativeButton(R.string.dialog_cancel_confirm, null)
                .show()

        }
    }

    private fun setupBottomNav(){
        mFragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val agendaFragment = AgendaFragment()
        val ponentesFragment = PonentesFragment()
        val descargasFragment = DescargasFragment()

        mActiveFragment = homeFragment

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, descargasFragment, DescargasFragment::class.java.name)
            .hide(descargasFragment).commit()
        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, ponentesFragment, PonentesFragment::class.java.name)
            .hide(ponentesFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, agendaFragment, AgendaFragment::class.java.name)
            .hide(agendaFragment).commit()


        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, homeFragment, HomeFragment::class.java.name).commit()



        mBinding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.action_home -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(homeFragment).commit()
                    mActiveFragment = homeFragment
                    true
                }
                R.id.action_agenda -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(agendaFragment).commit()
                    mActiveFragment = agendaFragment
                    true
                }
                R.id.action_ponentes -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(ponentesFragment).commit()
                    mActiveFragment = ponentesFragment
                    true
                }

                R.id.action_descargas -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(descargasFragment).commit()
                    mActiveFragment = descargasFragment
                    true
                }


                else -> false
            }
        }


    }
}