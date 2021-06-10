package com.farahaniconsulting.shopo.data.repository.extension

import com.google.gson.Gson

fun <T> String.toJsonObject(jsonString: String, clazz: Class<Array<T>>) : Array<T> =
    Gson().fromJson(jsonString, clazz)