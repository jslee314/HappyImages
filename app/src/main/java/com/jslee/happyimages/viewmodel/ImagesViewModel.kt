package com.jslee.happyimages.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jslee.happyimages.data.Repository
import com.jslee.happyimages.framework.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ImagesViewModel(private val repository: Repository): ViewModel() {
   // val imageList = repository.getPagingImageList().asLiveData()


    private var _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private var _url = MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            try {
                refreshVideos()
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
            }
        }
    }


    private suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            _title.postValue(RetrofitApi.retrofitApi.getImageList(5,100)[1].author)
             _url.postValue(RetrofitApi.retrofitApi.getImageList(5, 100)[1].url)
        }
    }


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