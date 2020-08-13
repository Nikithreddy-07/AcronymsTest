package com.learning.acronyms.networking

import com.learning.acronyms.model.AcronymsResponce
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("software/acromine/dictionary.py")
    fun getAcronyms(@Query("sf") keyword:String): Observable<List<AcronymsResponce>>
}