package com.example.provatecnicaapp2u.model

import com.google.gson.annotations.SerializedName

data class Photographer(
    @SerializedName("id") val id: Int,
    @SerializedName("guid") val guid: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("is_removed") val is_removed: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("image") val image: String,
    @SerializedName("facebook") val facebook: String?,
    @SerializedName("instagram") val instagram: String?,
    @SerializedName("webpage") val webpage: String?
)
