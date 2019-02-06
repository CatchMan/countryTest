package com.example.data.countries

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type
import java.util.Collections

object BorderConverters {

    @TypeConverter
    fun stringToBorderList(data: String?): List<String> {
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<String>>() {

        }.type

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun BorderListToString(someObjects: List<String>): String {
        return Gson().toJson(someObjects)
    }
}
