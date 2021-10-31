package com.example.gbapp6.presentation.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbapp6.R
import com.example.gbapp6.databinding.FragmentDictionaryListBinding
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.presentation.common.AbsFragment
import com.example.gbapp6.presentation.dictionary.adapter.DictionaryListAdapter
import javax.inject.Inject

class DictionaryListFragment :
    AbsFragment<DictionaryListView, DictionaryPresenter>(R.layout.fragment_dictionary_list),
    DictionaryListView {
    @Inject
    lateinit var dictionaryPresenter: DictionaryPresenter

    private var _binding: FragmentDictionaryListBinding? = null

    private val binding get() = _binding!!

    private val dictionaryListAdapter = DictionaryListAdapter()

    override fun createPresenter(): DictionaryPresenter = dictionaryPresenter

    override fun getCurrentView(): DictionaryListView = this

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

        binding.searchBar.setEndIconOnClickListener {
            val text: String? = binding.searchBar.editText?.text?.toString()
            text?.let { word -> getPresenterOrNull()?.getData(word) }
        }

    }

    override fun renderList(list: List<DataModel>) {
        dictionaryListAdapter.submitList(list)
    }

    override fun renderError(throwable: Throwable) {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
