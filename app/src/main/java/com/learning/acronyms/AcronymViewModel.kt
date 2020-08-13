package com.learning.acronyms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
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