package com.example.gbapp6.presentation.dictionary

import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.presentation.common.View

interface DictionaryListView : View {

    fun renderList(list: List<DataModel>)

    fun renderError(throwable: Throwable)

}
