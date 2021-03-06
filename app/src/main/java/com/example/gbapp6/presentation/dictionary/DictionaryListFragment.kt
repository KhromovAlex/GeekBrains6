package com.example.gbapp6.presentation.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbapp6.R
import com.example.gbapp6.databinding.FragmentDictionaryListBinding
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.presentation.dictionary.adapter.DictionaryListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DictionaryListFragment :
    Fragment(R.layout.fragment_dictionary_list) {

    private val viewModel: DictionaryListViewModel by viewModel()

    private var _binding: FragmentDictionaryListBinding? = null

    private val binding get() = _binding!!

    private val dictionaryListAdapter = DictionaryListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dictionaryList.layoutManager = LinearLayoutManager(requireContext())
        binding.dictionaryList.adapter = dictionaryListAdapter

        viewModel.liveData.observe(viewLifecycleOwner, ::renderData)

        binding.searchBar.setEndIconOnClickListener {
            val text: String? = binding.searchBar.editText?.text?.toString()
            text?.let { word -> viewModel.getData(word) }
        }

    }

    private fun renderData(appState: AppState<List<DataModel>>) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                dictionaryListAdapter.submitList(appState.data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
