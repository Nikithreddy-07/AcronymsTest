package com.learning.acronyms

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("software/acromine/dictionary.py")
    fun getAcronyms(@Query("sf") keyword:String): Observable<List<AcronymsResponce>>
}