package com.example.gbapp6.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gbapp6.R
import com.example.gbapp6.databinding.FragmentHistoryDetailsBinding
import com.example.gbapp6.domain.entity.DataModel

class HistoryDetailsFragment :
    Fragment(R.layout.fragment_history_details) {

    companion object {
        const val urlImage = "https://picsum.photos/200"
    }

    private var _binding: FragmentHistoryDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments?.getParcelable<DataModel>("details")

        Glide.with(view)
            .load(urlImage)
            .into(binding.image)

        binding.word.text = arguments?.text
        binding.description.text = arguments
            ?.meanings
            ?.mapNotNull { it.translation?.translation }
            ?.joinToString(", ")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
