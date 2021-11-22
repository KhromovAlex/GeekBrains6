package com.example.gbapp6.presentation.history.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.gbapp6.domain.entity.DataModel

class HistoryListDiffUtil(
    private val oldList: List<DataModel>,
    private val newList: List<DataModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text == newList[newItemPosition].text
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
