package com.farahaniconsulting.shopo.model


import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import com.google.gson.annotations.SerializedName

data class ShoppingItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("qrUrl")
    val qrUrl: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
) {
    fun toDTO() = ShoppingItemDTO(
        id = id,
        name = name,
        price = price,
        qrUrl = qrUrl,
        thumbnail = thumbnail)
}