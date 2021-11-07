package com.example.gbapp6.presentation.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gbapp6.R
import com.example.gbapp6.databinding.FragmentStopwatchBinding
import com.example.gbapp6.domain.entity.AppState
import org.koin.androidx.viewmodel.ext.android.viewModel

class StopwatchFragment :
    Fragment(R.layout.fragment_stopwatch) {

    private val viewModel: StopwatchViewModel by viewModel()

    private var _binding: FragmentStopwatchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStopwatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.watch()

        binding.btnStart.setOnClickListener { viewModel.start() }
        binding.btnPause.setOnClickListener { viewModel.pause() }
        binding.btnStop.setOnClickListener { viewModel.stop() }

        viewModel.liveData.observe(viewLifecycleOwner, ::renderData)
    }

    private fun renderData(appState: AppState<Long>) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                binding.output.text = tickerToString(appState.data)
            }
        }
    }

    private fun tickerToString(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
