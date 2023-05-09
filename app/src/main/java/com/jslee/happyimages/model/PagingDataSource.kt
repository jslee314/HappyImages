package com.jslee.happyimages.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jslee.happyimages.framework.Service
import kotlinx.coroutines.delay
import java.lang.Exception

class PagingDataSource(val service: Service) : PagingSource<Int, Picsum>() {

    val NETWORK_PAGE_SIZE = 9
    val INITIAL_LOAD_SIZE = 1

    override fun getRefreshKey(state: PagingState<Int, Picsum>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picsum> {
        return try {
            delay(200)

            val position = params.key ?: INITIAL_LOAD_SIZE
            val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

            val jsonResponse = service.getImageList(page = offset, limit = params.loadSize)
            val nextKey = if (jsonResponse.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = jsonResponse,
                prevKey = null, // Only paging forward.
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}