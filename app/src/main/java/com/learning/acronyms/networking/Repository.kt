package com.learning.acronyms.networking

import com.google.gson.GsonBuilder
import com.learning.acronyms.model.AcronymsResponce
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    companion object Factory {
        private val gson = GsonBuilder().setLenient().create()
        private const val baseUrl = "http://www.nactem.ac.uk"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build()
            return  retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadAcronyms(keyword:String) : Observable<List<AcronymsResponce>> =  create()
        .getAcronyms(keyword)

}