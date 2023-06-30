package com.lagn.simposioburo.domain.model

import com.google.gson.annotations.SerializedName

class LoginModel {
    @SerializedName("email")
    private var email: String = ""

    fun LoginModel(email: String) {
        this.email = email
    }

    fun getEmail(): String? {
        return this.email
    }
}