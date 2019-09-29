package com.example.stopwatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.stopwatch.R
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.view.listener.StopwatchListener
import com.example.stopwatch.viewmodel.StopwatchViewModel

class MainActivity : AppCompatActivity(), StopwatchListener {

    private lateinit var viewModel: StopwatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(StopwatchViewModel::class.java)

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
}
