package com.example.characters.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utility {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatCreatedDate(inputDate: String) : String {
        val zonedDateTime = ZonedDateTime.parse(inputDate)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z")
        val formattedDate = zonedDateTime.format(formatter)

        return formattedDate
    }
}