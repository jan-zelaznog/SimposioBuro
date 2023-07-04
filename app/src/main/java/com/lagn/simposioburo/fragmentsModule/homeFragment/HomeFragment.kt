package com.lagn.simposioburo.fragmentsModule.homeFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.FragmentDescargasBinding
import com.lagn.simposioburo.databinding.FragmentHomeBinding
import com.lagn.simposioburo.fragmentsModule.HomeActivity
import com.lagn.simposioburo.fragmentsModule.descargasFragment.adapter.AdapterDescargas


class HomeFragment : Fragment(), View.OnClickListener {
    var fragmentParent:HomeActivity? = null
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
        mBinding.cv13DeJulio.setOnClickListener(this@HomeFragment)
        mBinding.cv14De.setOnClickListener(this@HomeFragment)
        mBinding.cvTheSt.setOnClickListener(this@HomeFragment)
    }

    override fun onClick(p0: View?) {
        if (p0 == mBinding.cv13DeJulio) {
            this.fragmentParent?.changeFragmentToWorkshops()
        }
        else if (p0 == mBinding.cv14De) {
            this.fragmentParent?.changeFragmentToConferences()
        }
        else {
            val uri = "https://www.google.com/maps/dir/19.6942526,-98.8693617/the+st+regis+mexico+city/@19.5678303,-99.15747,11z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x85d1ff4be2f41ead:0xe7179355336f6a97!2m2!1d-99.1720892!2d19.4256338?entry=ttu"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(Intent.createChooser(intent, "Choose browser"))
        }
    }


}