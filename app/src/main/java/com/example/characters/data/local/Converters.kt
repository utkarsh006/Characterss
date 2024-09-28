package com.example.characters.data.local

import androidx.room.TypeConverter
import com.example.characters.data.remote.dto.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromLocation(location: Location?): String? {
        return location?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toLocation(locationString: String?): Location? {
        return locationString?.let {
            val type = object : TypeToken<Location>() {}.type
            gson.fromJson(it, type)
        }
    }
}
