package com.jslee.happyimages.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jslee.happyimages.framework.RetrofitApi
import com.jslee.happyimages.framework.Service
import com.jslee.happyimages.model.PagingDataSource
import com.jslee.happyimages.model.Picsum
import kotlinx.coroutines.flow.Flow

class Repository {

    private val myService = object: Service {

        override suspend fun getImageList(page: Int, limit: Int): List<Picsum> {
            val list = mutableListOf<Picsum>()
            val imageList = RetrofitApi.retrofitApi.getImageList(page,limit)

            for(randIdx in 0 until 9) {
                list.add(Picsum(
                    imageList[randIdx].id,
                    imageList[randIdx].author,
                    imageList[randIdx].width,
                    imageList[randIdx].id.length,
                    imageList[randIdx].url,
                    imageList[randIdx].download_url))
            }
            return list
        }
    }

    fun getMyModelList(): Flow<PagingData<Picsum>> {
        return Pager(PagingConfig(pageSize = 10, prefetchDistance = 3, enablePlaceholders = true)) {
            PagingDataSource(myService)
        }.flow
    }
}