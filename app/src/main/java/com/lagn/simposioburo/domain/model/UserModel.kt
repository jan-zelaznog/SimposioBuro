package com.lagn.simposioburo.domain.model

import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("nombre")
    private var nombre: String? = null

    @SerializedName("apellido_paterno")
    private var apellido_paterno: String? = null

    @SerializedName("apellido_materno")
    private var apellido_materno: String? = null

    @SerializedName("email")
    private var email: String? = null

    @SerializedName("empresa")
    private var empresa: String? = null

    @SerializedName("puesto")
    private var puesto: String? = null

    @SerializedName("telefono")
    private var telefono: String? = null

    @SerializedName("asistencia")
    private var asistencia: String? = null

    fun UserModel(nombre: String?, apellido_paterno: String?, apellido_materno: String?,
                  email: String?, empresa: String?, puesto: String?, telefono: String?, asistencia: String?) {
        this.nombre = nombre
        this.apellido_paterno = apellido_paterno
        this.apellido_materno = apellido_materno
        this.email = email
        this.empresa = empresa
        this.puesto = puesto
        this.telefono = telefono
        this.asistencia = asistencia
    }

    fun getNombre(): String? {
        return nombre
    }

    fun getApPaterno(): String? {
        return apellido_paterno
    }

    fun getApMaterno(): String? {
        return apellido_materno
    }

    fun getEmail(): String? {
        return email
    }

    fun getEmpresa(): String? {
        return empresa
    }

    fun getPuesto(): String? {
        return puesto
    }

    fun getTelefono(): String? {
        return telefono
    }

    fun getAsistencia(): String? {
        return asistencia
    }

}
