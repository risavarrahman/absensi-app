package com.mufiid.absensi_app.ui.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mufiid.absensi_app.data.source.BaseRepository

class AttendanceViewModel(private val repo: BaseRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}