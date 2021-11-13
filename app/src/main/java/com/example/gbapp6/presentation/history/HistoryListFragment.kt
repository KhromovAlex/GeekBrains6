package com.example.gbapp6.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbapp6.R
import com.example.gbapp6.databinding.FragmentHistoryListBinding
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.presentation.history.adapter.HistoryListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryListFragment :
    Fragment(R.layout.fragment_history_list) {

    private val viewModel: HistoryListViewModel by viewModel()

    private var _binding: FragmentHistoryListBinding? = null

    private val binding get() = _binding!!

    private val historyListAdapter = HistoryListAdapter()

    private var nav: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nav = Navigation.findNavController(view)

        if (savedInstanceState == null) {
            viewModel.getData().observe(viewLifecycleOwner, ::renderData)
        }

        binding.historyList.layoutManager = LinearLayoutManager(requireContext())
        binding.historyList.adapter = historyListAdapter

        viewModel.liveData.observe(viewLifecycleOwner, ::toDetails)

        binding.searchBar.setEndIconOnClickListener {
            val text: String? = binding.searchBar.editText?.text?.toString()
            text?.let { word -> viewModel.searchData(word) }
        }

    }

    private fun toDetails(appState: AppState<DataModel>) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Undefined", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                val bundle = bundleOf("details" to appState.data)
                nav?.navigate(R.id.historyDetailsListFragment, bundle)
                viewModel.clearAppState()
            }
        }
    }

    private fun renderData(appState: AppState<List<DataModel>>) {
        when (appState) {
            is AppState.Success -> {
                historyListAdapter.submitList(appState.data)
            }
            else -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
