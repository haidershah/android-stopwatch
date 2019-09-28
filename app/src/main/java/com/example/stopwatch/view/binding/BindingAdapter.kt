package com.example.stopwatch.view.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.stopwatch.R

@BindingAdapter("timeElapsed")
fun TextView.timeElapsed(timeElapsed: Int) {
    val hours = timeElapsed / 3600
    val minutes = (timeElapsed - (hours * 3600)) / 60
    val seconds = timeElapsed - (hours * 3600) - (minutes * 60)

    text = context.getString(R.string.time_elapsed, hours, minutes, seconds)
}
