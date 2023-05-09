package com.jslee.happyimages

import android.app.Application
import com.jslee.happyimages.data.Repository

class AppApplication: Application() {
    val repository = Repository()
}