package com.jslee.happyimages.framework

import com.jslee.happyimages.model.Picsum
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("v2/list")
    suspend fun getImageList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Picsum>
}

object RetrofitApi {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://picsum.photos/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val retrofitApi = retrofit.create(Service::class.java)

}