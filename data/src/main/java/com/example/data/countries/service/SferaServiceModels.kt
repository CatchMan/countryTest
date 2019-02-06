package com.example.data.countries.service


data class CountryInfo(
        val id: Long,
        var alpha3Code: String,
        var borders: List<String>,
        var nativeName: String
)
