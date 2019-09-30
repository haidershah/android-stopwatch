package com.example.stopwatch.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.stopwatch.R
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.view.listener.StopwatchListener
import com.example.stopwatch.viewmodel.StopwatchViewModel
import com.example.stopwatch.viewmodel.factory.StopWatchViewModelFactory

class MainActivity : AppCompatActivity(), StopwatchListener {

    private lateinit var viewModel: StopwatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val savedTimeElapsed = sharedPref.getLong(getString(R.string.saved_time_elapsed), 0)
        val savedTimeStopWatchClosed = sharedPref.getLong(getString(R.string.saved_time_stop_watch_closed), 0)
        val isStopwatchStartedKey = sharedPref.getBoolean(getString(R.string.is_stopwatch_started), false)

        viewModel = ViewModelProviders
            .of(this, StopWatchViewModelFactory(savedTimeElapsed, isStopwatchStartedKey,
                savedTimeStopWatchClosed))
            .get(StopwatchViewModel::class.java)

        binding.viewModel = viewModel
        binding.listener = this
        binding.lifecycleOwner = this
    }

    override fun onStartStopwatchClicked() {
        viewModel.startStopStopwatch()
    }

    override fun onResetClicked() {
        viewModel.reset()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveToSharedPref()
    }

    private fun saveToSharedPref() {
        val savedTimeElapsedKey = getString(R.string.saved_time_elapsed)
        val savedTimeElapsedValue = viewModel.timeElapsed.value ?: 0

        val isStopwatchStartedKey = getString(R.string.is_stopwatch_started)
        val isStopwatchStartedValue = viewModel.btnText.value == R.string.stop

        val savedTimeStopWatchClosedKey = getString(R.string.saved_time_stop_watch_closed)
        val savedTimeStopWatchClosedValue = System.currentTimeMillis()

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putLong(savedTimeElapsedKey, savedTimeElapsedValue)
            .putBoolean(isStopwatchStartedKey, isStopwatchStartedValue)
            .putLong(savedTimeStopWatchClosedKey, savedTimeStopWatchClosedValue)
            .apply()
    }
}
