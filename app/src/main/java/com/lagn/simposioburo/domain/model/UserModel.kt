package com.lagn.simposioburo.domain.model

import com.google.gson.annotations.SerializedName
data class UserModel(
    val nom:String, val apellidoP:String,
    val apellidoM:String, val mail:String, val Empresa:String, val phone:String,
    val Puesto:String, val Asistencia: String, val talleres:Array<Int>)
{
    @SerializedName("email")
    private var email: String = ""

    @SerializedName("nombre")
    private var nombre: String = ""

    @SerializedName("apellido_paterno")
    private var apellido_paterno: String = ""

    @SerializedName("apellido_materno")
    private var apellido_materno: String = ""

    @SerializedName("empresa")
    private var empresa: String = ""

    @SerializedName("telefono")
    private var telefono: String = ""

    @SerializedName("puesto")
    private var puesto: String = ""

    @SerializedName("asistencia")
    private var asistencia: String = ""

    @SerializedName("talleres_ids")
    private var talleres_ids: Array<Int> = arrayOf(0)

    init {
        this.email = mail
        this.nombre = nom
        this.apellido_paterno = apellidoP
        this.apellido_materno = apellidoM
        this.empresa = Empresa
        this.puesto = Puesto
        this.telefono = phone
        this.asistencia = Asistencia
        this.talleres_ids = talleres
    }
}
