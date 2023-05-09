package com.jslee.happyimages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.happyimages.data.Repository

class WebViewViewModel (private val myRepository: Repository): ViewModel(){
}

class WebViewViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WebViewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WebViewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}