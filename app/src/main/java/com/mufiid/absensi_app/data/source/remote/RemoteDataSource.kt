package com.mufiid.absensi_app.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.HttpException
import com.mufiid.absensi_app.api.ApiConfig
import com.mufiid.absensi_app.data.source.local.entity.UserEntity
import com.mufiid.absensi_app.data.source.remote.response.ApiResponse
import java.io.IOException

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun loginUser(email: String?, password: String?) : LiveData<ApiResponse<UserEntity>> {
        val result = MutableLiveData<ApiResponse<UserEntity>>()

        try {
            val data = ApiConfig.instance().login(email, password)
            when (data.meta?.code) {
                200 -> result.value = ApiResponse.success(data.data)
                404 -> result.value = ApiResponse.empty(data.meta.message)
                else -> result.value = ApiResponse.failed(data.meta?.message)
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> result.value = ApiResponse.error("Network Error")
                is HttpException -> {
                    val code = throwable.statusCode
                    val errorResponse = throwable.message
                    result.value = ApiResponse.error("Error $errorResponse")
                }
                else -> result.value = ApiResponse.error("Unknown error")
            }
        }

        return result
    }
}