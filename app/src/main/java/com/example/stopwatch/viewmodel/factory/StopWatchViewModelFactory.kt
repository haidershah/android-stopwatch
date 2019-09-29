package com.example.stopwatch.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.viewmodel.StopwatchViewModel
import java.lang.IllegalArgumentException

class StopWatchViewModelFactory(
    private val savedTimeElapsed: Int,
    private val isStopwatchStarted: Boolean
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StopwatchViewModel::class.java)) {
            return StopwatchViewModel(savedTimeElapsed, isStopwatchStarted) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
