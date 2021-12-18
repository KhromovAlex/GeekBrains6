package com.example.gbapp6.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.database.table.Definition
import com.example.database.table.DefinitionModel
import com.example.database.table.Meanings as MeaningsDb
import com.example.gbapp6.domain.entity.Meanings as MeaningsEntity
import com.example.database.table.Translation as TranslationDb
import com.example.gbapp6.domain.entity.Translation as TranslationEntity
import com.example.gbapp6.data.datasource.DictionaryLocalDataSource
import com.example.gbapp6.data.datasource.DictionaryRemoteDataSource
import com.example.gbapp6.domain.entity.DataModel

class DictionaryRepositoryImpl(
    private val dictionaryRemoteDataSource: DictionaryRemoteDataSource,
    private val dictionaryLocalDataSource: DictionaryLocalDataSource,
) : DictionaryRepository {

    override suspend fun getData(word: String): List<DataModel> =
        dictionaryRemoteDataSource.getData(word)

    override fun insertAllInHistory(list: List<DataModel>) {
        dictionaryLocalDataSource.insertAllDefinitionModel(list.map(::mapEntityToDb))
    }

    override fun getAllHistory(): LiveData<List<DataModel>> =
        Transformations.map(dictionaryLocalDataSource.getAll()) {
            it.map(::mapDbToEntity)
        }

    override fun getHistoryByText(text: String): DataModel =
        mapDbToEntity(dictionaryLocalDataSource.getByText(text))

    private fun mapEntityToDb(entityModel: DataModel): com.example.database.table.DefinitionModel =
        com.example.database.table.DefinitionModel(
            definition = com.example.database.table.Definition(
                id = entityModel.id,
                text = entityModel.text,
            ),
            meanings = entityModel.meanings?.map {
                MeaningsDb(
                    id = it.id,
                    definitionId = entityModel.id,
                    translation = TranslationDb(
                        translation = it.translation?.translation
                    )
                )
            },
        )

    private fun mapDbToEntity(dbModel: com.example.database.table.DefinitionModel): DataModel =
        DataModel(
            id = dbModel.definition.id,
            text = dbModel.definition.text,
            meanings = dbModel.meanings?.map {
                MeaningsEntity(
                    id = it.id,
                    translation = TranslationEntity(
                        translation = it.translation?.translation
                    ),
                )
            }
        )


}
