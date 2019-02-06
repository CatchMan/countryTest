package com.example.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.data.countries.storage.CountriesDao
import com.example.data.countries.storage.StorageCountries




@Database(entities = [(StorageCountries::class)], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCountriesDao(): CountriesDao
}