package org.sopt.app3.planfit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.model.MainGet
import org.sopt.app3.planfit.domain.model.MainPut
import org.sopt.app3.planfit.domain.repo.MainRepository
import retrofit2.HttpException

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val _main = MutableLiveData<MainGet>()
    val main: LiveData<MainGet> get() = _main

    fun getMain() = viewModelScope.launch {
        mainRepository.getMain()
            .onSuccess {
                _main.value = it
            }
            .onFailure {
                if(it is HttpException){
                    Log.e("서버에러", it.message.toString())
                }
            }
    }

    fun putMain(mainPut: MainPut) = viewModelScope.launch {
        mainRepository.putMain(mainPut).onSuccess {
            getMain()
        }
    }
}