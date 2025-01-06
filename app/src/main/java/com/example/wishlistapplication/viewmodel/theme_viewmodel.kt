package com.example.wishlistapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {

    // Light Theme
    private val _radioButton1 = MutableLiveData<Boolean>()
    val radioButton1: LiveData<Boolean> = _radioButton1
    fun setRadioButton1(value: Boolean) {
        _radioButton1.value = value
    }

    // Warm Theme
    private val _radioButton2 = MutableLiveData<Boolean>()
    val radioButton2: LiveData<Boolean> = _radioButton2
    fun setRadioButton2(value: Boolean) {
        _radioButton2.value = value
    }

    // Dark Theme
    private val _radioButton3 = MutableLiveData<Boolean>()
    val radioButton3: LiveData<Boolean> = _radioButton3
    fun setRadioButton3(value: Boolean) {
        _radioButton3.value = value
    }
}