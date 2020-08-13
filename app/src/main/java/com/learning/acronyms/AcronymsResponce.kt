package com.learning.acronyms

data class AcronymsResponce (
    val sf:String, val ifs:List<Acronym>)

data class Acronym (
    val IF:String,
    val freq:Int,
    val since:Int)
