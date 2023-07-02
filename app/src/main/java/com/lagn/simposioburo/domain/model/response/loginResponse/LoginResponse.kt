package com.lagn.simposioburo.domain.model.response.loginResponse

data class LoginResponse(
    val access_token: String,
    val status: String,
    val user: User
)