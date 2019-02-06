package com.example.data

class ApiConst private constructor() {

    init {
        throw AssertionError("Don't make entity of " + ApiConst::class.java.simpleName)
    }

    companion object {

        const val BASE_URL = "https://restcountries.eu/"
        const val ENDPOINT = BASE_URL + ""

    }


}
