package com.example.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StopwatchViewModel : ViewModel() {

    private val _timeElapsed = MutableLiveData<Int>()
    val timeElapsed: LiveData<Int> = _timeElapsed

    fun startStopwatch() {
        viewModelScope.launch {
            var timeElapsed = 0

            while (true) {

                withContext(Dispatchers.IO) {
                    delay(1_000)
                    timeElapsed++
                }

                _timeElapsed.value = timeElapsed
            }
        }
    }
}
