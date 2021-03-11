package com.mufiid.absensi_app.data.source

import androidx.lifecycle.LiveData
import com.mufiid.absensi_app.data.source.local.entity.TaskEntity
import com.mufiid.absensi_app.data.source.local.entity.UserEntity
import com.mufiid.absensi_app.data.source.remote.response.ApiResponse

interface BaseDataSource {
    suspend fun loginUser(email: String?, password: String?): LiveData<ApiResponse<UserEntity>>
    suspend fun getAllTaskData(
        token: String,
        userId: Int?,
        date: String? = null,
        isAdmin: Int? = null
    ): LiveData<ApiResponse<List<TaskEntity>>>
    suspend fun getEmployee(
        token: String
    ): LiveData<ApiResponse<List<UserEntity>>>
}