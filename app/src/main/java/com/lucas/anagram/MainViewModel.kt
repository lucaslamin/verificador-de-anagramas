package com.lucas.anagram

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _resultLiveData = MutableLiveData<Boolean>()

    val resultLiveData: LiveData<Boolean> = _resultLiveData

    fun checkIfIsAnagram(firstString: String, secondString: String) {
        if (firstString.isNotBlank() && secondString.isNotBlank())
            _resultLiveData.value = firstString.lowercase().toSortedSet() == secondString.lowercase().toSortedSet()
    }
}