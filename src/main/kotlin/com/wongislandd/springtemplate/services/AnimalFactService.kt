package com.wongislandd.springtemplate.services

import com.wongislandd.springtemplate.clients.AnimalFactClient
import com.wongislandd.springtemplate.models.AnimalType
import com.wongislandd.springtemplate.transformer.AnimalFactTransformer
import com.wongislandd.springtemplate.utils.Logger
import org.springframework.stereotype.Service

/**
 * Manipulate data retrieved from clients
 */
@Service
class AnimalFactService(
    private val animalFactClient: AnimalFactClient,
    private val animalFactTransformer: AnimalFactTransformer
) : Logger() {

    fun getAnimalFact(animalType: AnimalType): String {
        val response = animalFactClient.loadAnimalFact(animalType)
        val internalModel = animalFactTransformer.transform(response)
        return internalModel.fact
    }
}