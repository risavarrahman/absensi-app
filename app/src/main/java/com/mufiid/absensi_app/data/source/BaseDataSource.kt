package com.mufiid.absensi_app.data.source

import androidx.lifecycle.LiveData
import com.mufiid.absensi_app.data.source.local.entity.UserEntity
import com.mufiid.absensi_app.data.source.remote.response.ApiResponse

interface BaseDataSource {
    suspend fun loginUser(email: String?, password: String?): LiveData<ApiResponse<UserEntity>>
}