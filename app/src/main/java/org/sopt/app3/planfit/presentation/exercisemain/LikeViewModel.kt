package org.sopt.app3.planfit.presentation.exercisemain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.repo.LikeRepo

class LikeViewModel(private val likeRepo: LikeRepo) : ViewModel() {
    private val _likeStatus = MutableLiveData(false)

    fun changeToLike(id: Long) {
        viewModelScope.launch {
            likeRepo.changeToLike(id)
                .onSuccess {
                    _likeStatus.value = true
                }
                .onFailure {
                    Log.e("change status fail", it.message.toString())
                }
        }
    }

    fun changeToUnlike(id: Long) {
        viewModelScope.launch {
            likeRepo.changeToUnLike(id)
                .onSuccess {
                    _likeStatus.value = false
                }
                .onFailure {
                    Log.e("change status fail", it.message.toString())
                }
        }
    }
}