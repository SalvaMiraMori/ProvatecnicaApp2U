package com.example.provatecnicaapp2u.model

import com.google.gson.annotations.SerializedName

data class GetPhotographersResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Photographer>
)
