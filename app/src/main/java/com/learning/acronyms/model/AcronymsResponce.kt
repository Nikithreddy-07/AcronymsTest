package com.learning.acronyms.model

data class AcronymsResponce (
    val sf:String, val lfs:List<Acronym>)

data class Acronym (
    val lf:String,
    val freq:Int,
    val since:Int)
