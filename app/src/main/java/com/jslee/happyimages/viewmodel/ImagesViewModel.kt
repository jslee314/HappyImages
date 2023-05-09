package com.jslee.happyimages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.happyimages.data.Repository

class ImagesViewModel(private val repository: Repository): ViewModel() {

}

class ImagesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    // ViewModelProvider.Factory를 확장함.
    // 오버라이드 하면 아래와 같은 create 함수를 받을 수 있음.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // modelClass에 MainActivityViewModel이 상속이 되었나요? 라고 물어봅니다.
        if (modelClass.isAssignableFrom(ImagesViewModel::class.java)) {
            // 맞다면 MainViewModel의 파라미터 값을 넘겨주죠.
            @Suppress("UNCHECKED_CAST")
            return ImagesViewModel(repository) as T
        }
        // 상속이 되지 않았다면 IllegalArgumentException을 통해 상속이 되지 않았다는 에러를 띄웁니다
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}