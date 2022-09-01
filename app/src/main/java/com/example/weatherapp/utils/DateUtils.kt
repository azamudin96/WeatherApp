package com.example.weatherapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat

object DateUtils {

    fun formateDate(dateTime: String, dateFormat: String, field: String): String? {
        val input = SimpleDateFormat(dateFormat)
        val output = SimpleDateFormat(field)
        try {
            val getAbbreviate = input.parse(dateTime)    // parse input
            return output.format(getAbbreviate)    // format output
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}