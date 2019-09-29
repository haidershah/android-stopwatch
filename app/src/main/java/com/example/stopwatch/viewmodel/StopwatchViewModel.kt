package com.example.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopwatch.R
import kotlinx.coroutines.*

class StopwatchViewModel : ViewModel() {

    private val _timeElapsed = MutableLiveData<Int>()
    val timeElapsed: LiveData<Int> = _timeElapsed

    private val _btnText = MutableLiveData<Int>()
    val btnText: LiveData<Int> = _btnText

    private lateinit var job: Job

    init {
        _btnText.value = R.string.start
        _timeElapsed.value = 0
    }

    fun startStopStopwatch() {
        if (::job.isInitialized && job.isActive) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        _btnText.value = R.string.stop
        job = Job()
        viewModelScope.launch {

            while (true) {

                withContext(Dispatchers.IO + job) {
                    delay(1_000)
                }

                _timeElapsed.value = _timeElapsed.value?.plus(1)
            }
        }
    }

    private fun stopTimer() {
        _btnText.value = R.string.start
        job.cancel()
    }
}
