package com.farahaniconsulting.shopo.ui

import android.content.Context
import timber.log.Timber
import java.io.IOException

fun Context.getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        Timber.d(ioException)
        return null
    }
    return jsonString
}