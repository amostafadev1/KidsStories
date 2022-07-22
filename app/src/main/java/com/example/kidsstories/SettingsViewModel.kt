package com.example.kidsstories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel:ViewModel() {
    var textSize = MutableLiveData<Float>()
}