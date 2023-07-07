package com.lagn.simposioburo.registrarModule

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.servicestest.Common.Services
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.lagn.simposioburo.MainActivity
import com.lagn.simposioburo.R
import com.lagn.simposioburo.databinding.ActivityRegistrarUsuarioBinding
import com.lagn.simposioburo.domain.model.ModelD
import com.lagn.simposioburo.domain.model.UserModel
import com.lagn.simposioburo.domain.model.response.talleresResp.Talleresresp
import com.lagn.simposioburo.fragmentsModule.HomeActivity
import com.lagn.simposioburo.services.Client
import com.lagn.simposioburo.util.PreferenceHelper
import com.lagn.simposioburo.util.PreferenceHelper.set
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class RegistrarUsuario : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityRegistrarUsuarioBinding
    private lateinit var mCardHolder: LinearLayout
    var client = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getWorkshops()
        materialSpinner()
        mCardHolder = mBinding.cardHolder
        mBinding.tvRegresar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
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
                    mBinding.tilPuesto,
                    mBinding.tilConfirmaCorreo,
                    mBinding.tilCorreoElectronico,
                    mBinding.tilAPaterno,
                    mBinding.tilNombre
                )
            ) {
                val asistencia = mBinding.etTalleres.text
                if (!asistencia.contains("Talleres")) {
                    val userReg = UserModel(
                        mBinding.tilNombre.editText?.text.toString(),
                        mBinding.tilAPaterno.editText?.text.toString(),
                        mBinding.tilAMaterno.editText?.text.toString(),
                        mBinding.tilCorreoElectronico.editText?.text.toString(),
                        mBinding.tilEmpresa.editText?.text.toString(),
                        mBinding.tilTelefono.editText?.text.toString(),
                        mBinding.tilPuesto.editText?.text.toString(),
                        "14",
                        arrayOf(0)
                    )
                    doRegister(userReg)
                }
                else {
                    var workshopsSelected = mutableListOf<Int>()
                    for (i in 0..mCardHolder.childCount-1) {
                        val child: View = mCardHolder.getChildAt(i);
                        if (child is CardView) {
                            if (child.isSelected) {
                                val linearLayout = child.getChildAt(0) as LinearLayout
                                val txtID = linearLayout.findViewWithTag<TextView>("1D")
                                val strID = txtID.text?.toString()
                                strID?.let { it1 -> workshopsSelected.add(it1.toInt()) }
                            }
                        }
                    }
                    if (workshopsSelected.isEmpty() || workshopsSelected.count() > 3) {
                        MaterialAlertDialogBuilder(this@RegistrarUsuario)
                            .setTitle("Debe seleccionar por lo menos un taller y máximo 3")
                            .setPositiveButton(R.string.dialog_ok) { _, _ ->
                            }
                            .show()
                    }
                    else {
                        val asistSTR = if (asistencia.contains("y Plenaria")) "13_14" else "13"
                        val userReg = UserModel(
                            mBinding.tilNombre.editText?.text.toString(),
                            mBinding.tilAPaterno.editText?.text.toString(),
                            mBinding.tilAMaterno.editText?.text.toString(),
                            mBinding.tilCorreoElectronico.editText?.text.toString(),
                            mBinding.tilEmpresa.editText?.text.toString(),
                            mBinding.tilTelefono.editText?.text.toString(),
                            mBinding.tilPuesto.editText?.text.toString(),
                            asistSTR,
                            workshopsSelected.toTypedArray()
                        )
                        doRegister(userReg)
                    }
                }
            }
        }
    }

    private fun doRegister(userReg:UserModel) {
        val call = client.getApiClient()?.create(Services::class.java)
        val cc = call?.setRegistro(userReg)
        cc?.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val resp_code = response.code()
                if (resp_code == 200 && response.body() != null) {
                    try {
                        val dictionary = JSONObject(response.body()!!.string())
                        Toast.makeText(this@RegistrarUsuario, "Success", Toast.LENGTH_LONG).show()
                        val status = dictionary.optString("status")
                        (if (status != "success") {
                            MaterialAlertDialogBuilder(this@RegistrarUsuario)
                                .setTitle("Correo electrónico no permitido")
                                .setPositiveButton(R.string.dialog_ok) { _, _ ->
                                }
                                .show()
                        } else {
                            val access_token = dictionary.optString("access_token")
                            createSesionPref(access_token)
                            goToMain()
                            val user = dictionary.optJSONObject("user")
                            if (user == null) {
                                Toast.makeText(this@RegistrarUsuario, "Usuario inválido", Toast.LENGTH_LONG).show()
                            } else {
                                val apellido_materno = user.optString("apellido_materno")
                                val apellido_paterno = user.optString("apellido_paterno")
                                val email = user.optString("email")
                                val empresa = user.optString("empresa")
                                val nombre = user.optString("nombre")
                            }

                        })

                    } catch (e: Exception) {
                        Toast.makeText(this@RegistrarUsuario, "Usuario inválido", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    var msg = "Ocurrió un error en el backend. Por favor reintente más tarde"
                    when (resp_code) {
                        400 -> { msg = "Correo electrónico ya fue registrado" }
                        401 -> { msg = "Correo electrónico no permitido" }
                    }
                    MaterialAlertDialogBuilder(this@RegistrarUsuario)
                        .setTitle(msg)
                        .setPositiveButton(R.string.dialog_ok) { _, _ ->
                        }
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@RegistrarUsuario, "Ocurrió un error en el backend. Por favor reintente más tarde", Toast.LENGTH_LONG).show()
            }
        })
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
                var header = ""
                var groupID = 0
                var itemID = 0
                val typeface = ResourcesCompat.getFont(this@RegistrarUsuario, com.lagn.simposioburo.R.font.montserrat_bold)
                for (taller in datos?.data!!) {
                    val tmpHeader = "Taller en horario " + taller.hora_inicio?.dropLast(3) + " " + taller.hora_fin?.dropLast(3)
                    if (!header.equals(tmpHeader)) {
                        header = tmpHeader
                        groupID += 1
                        itemID = 0
                        //talleresAdapter.add(TalleresAdapterItem(header))
                        val textView = TextView(this@RegistrarUsuario)
                        textView.text = header
                        textView.setTypeface(typeface)
                        textView.textSize = 13F
                        mCardHolder.addView(textView)
                    }
                    itemID += 1
                    val cardView = createCV(groupID, itemID, taller)
                    mCardHolder.addView(cardView)
                }
            }

            override fun onFailure(call: Call<Talleresresp>, t: Throwable) {
                Toast.makeText(this@RegistrarUsuario, "Error en el servidor", Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun validateFields(vararg textFields: TextInputLayout): Boolean {
        var isValid = true
        for (textField in textFields) {
            if (textField.editText?.text.toString().trim().isEmpty()) {
                textField.error = getString(R.string.helper_required)
                textField.requestFocus()
                isValid = false
            }
            else textField.error = null
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
                0,2  ->{
                    mBinding.lbl1.setVisibility(View.VISIBLE)
                    mBinding.lbl2.setVisibility(View.VISIBLE)
                    mBinding.cardHolder.setVisibility(View.VISIBLE)
                }
                1  ->{
                    mBinding.lbl1.setVisibility(View.GONE)
                    mBinding.lbl2.setVisibility(View.GONE)
                    mBinding.cardHolder.setVisibility(View.GONE)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        val cvTalleres = p0 as CardView
        val prevStatus = cvTalleres.isSelected
        val tag:String = cvTalleres.tag as String
        val chunks = tag.split("-")
        for (i in 1..3)  {
            val tag = chunks[0] + "-" + i
            val item = mCardHolder.findViewWithTag<CardView>(tag)
            item.isSelected = false
            item.background =
                ContextCompat.getDrawable(this, com.lagn.simposioburo.R.drawable.state_cv)
            val llayout = item.getChildAt(0) as LinearLayout
            for (ii in 0 ..llayout.childCount - 1) {
                val child:View  = llayout.getChildAt(ii);
                if (child is TextView) {
                    child.setTextColor(resources.getColor(R.color.gris_textos_eobs))
                }
            }
        }
        if (!prevStatus) {
            cvTalleres.isSelected = true
            cvTalleres.background =
                ContextCompat.getDrawable(this, com.lagn.simposioburo.R.drawable.gradiente_radius)
            val llayout = cvTalleres.getChildAt(0) as LinearLayout
            for (iii in 0..llayout.childCount - 1) {
                val child: View = llayout.getChildAt(iii);
                if (child is TextView) {
                    child.setTextColor(Color.WHITE)
                }
            }
        }
    }

    fun createCV(groupID: Int, itemID:Int, item:ModelD):CardView{
        val typefaceB = ResourcesCompat.getFont(this@RegistrarUsuario, com.lagn.simposioburo.R.font.montserrat_bold)
        val typefaceR = ResourcesCompat.getFont(this@RegistrarUsuario, com.lagn.simposioburo.R.font.montserrat_regular)
        val cardLinearLayout = LinearLayout(this@RegistrarUsuario)
        cardLinearLayout.orientation = LinearLayout.VERTICAL
        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(16,16,16,16)
        val cardView = CardView(this@RegistrarUsuario)
        cardView.tag = "$groupID-$itemID"
        cardView.radius = 6f
        cardView.background = resources.getDrawable(R.drawable.state_cv)
        cardView.setContentPadding(36,36,36,36)
        cardView.layoutParams = params
        cardView.cardElevation = 8f

        val desc = TextView(this@RegistrarUsuario)
        desc.text = item.titulo
        desc.textSize = 15f
        desc.setTextColor(resources.getColor(R.color.gris_textos_eobs))
        desc.setTypeface(typefaceB)

        val name = TextView(this@RegistrarUsuario)
        name.text = item.speaker
        name.textSize = 13f
        name.setTypeface(typefaceR)
        name.setTextColor(resources.getColor(R.color.gris_textos_eobs))

        val pos = TextView(this@RegistrarUsuario)
        pos.text = item.puesto
        pos.textSize = 11f
        pos.setTypeface(typefaceR)
        pos.setTextColor(resources.getColor(R.color.gris_textos_eobs))

        val tallerID = TextView(this@RegistrarUsuario)
        tallerID.tag = "1D"
        tallerID.text = item.id.toString()
        tallerID.visibility = View.GONE

        cardLinearLayout.addView(desc)
        cardLinearLayout.addView(name)
        cardLinearLayout.addView(pos)
        cardLinearLayout.addView(tallerID)
        cardView.addView(cardLinearLayout)
        if (item.cupos_disponibles > 0) {
            cardView.setOnClickListener(this@RegistrarUsuario)
        }
        return cardView
    }

    private fun goToMain(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun createSesionPref(token: String){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["access_token"]= token
    }
}