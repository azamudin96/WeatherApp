package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error" ) var error : Error? = Error()
)

data class Error (
    @SerializedName("code"    ) var code    : Int?    = null,
    @SerializedName("message" ) var message : String? = null
)
