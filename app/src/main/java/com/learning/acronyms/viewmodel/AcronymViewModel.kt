package com.learning.acronyms.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.acronyms.model.AcronymsResponce
import com.learning.acronyms.networking.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AcronymViewModel(
    val repository: Repository
): ViewModel(){

     val acronymsResponce = MutableLiveData<List<AcronymsResponce>>()
     val acronymsError = MutableLiveData<String>()


    fun getAcronyms(keyWord:String) {
        repository.loadAcronyms(keyWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
               this.acronymsResponce.value = it
            },{
                this.acronymsError.value = it.toString()
            })
    }
}

class AcronymViewModelFactory(
    private val repository: Repository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(AcronymViewModel:Class<T>): T {
        return AcronymViewModel(repository) as T
    }
}