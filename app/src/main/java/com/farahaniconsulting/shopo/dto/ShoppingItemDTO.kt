package com.farahaniconsulting.shopo.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingItemDTO(val id: String?,
                            val name: String?,
                            val price: String?,
                            val qrUrl: String?,
                            val thumbnail: String?
                            ) : Parcelable