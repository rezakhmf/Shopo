package com.farahaniconsulting.shopo.data.repository.extension

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

@Throws(IOException::class)
fun String.readFileFromResources(): String {
    var inputStream: InputStream? = null
    try {
        inputStream =
            javaClass.classLoader?.getResourceAsStream(this)
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))

        var str: String? = reader.readLine()
        while (str != null) {
            builder.append(str)
            str = reader.readLine()
        }
        return builder.toString()
    } finally {
        inputStream?.close()
    }
}