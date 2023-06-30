package com.example.servicestest.Common

import com.google.gson.JsonObject
import com.lagn.simposioburo.domain.model.LoginModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Services {

    @Headers("Content-Type: application/json")
    @POST ("/api/login")
    fun loginService(@Body loginObj: LoginModel) : Call<LoginModel>
}