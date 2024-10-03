package com.wongislandd.springtemplate.clients

import com.google.gson.Gson
import com.wongislandd.springtemplate.models.AnimalType
import com.wongislandd.springtemplate.models.ExternalAnimalFact
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.getForObject
import org.springframework.web.server.ResponseStatusException

/**
 * Fetch data from other APIs.
 */
@Service
class AnimalFactClient(private val gson: Gson) {

    fun loadAnimalFact(type: AnimalType): ExternalAnimalFact {
        val restTemplate = RestTemplateBuilder().errorHandler(
            TemplateModelClientErrorHandler(type)
        ).build()
        val response: String = restTemplate.getForObject(
            buildAnimalFactUrlForType(type)
        )
        val parsed: ExternalAnimalFact = gson.fromJson(response, ExternalAnimalFact::class.java)
        return parsed
    }

    private fun buildAnimalFactUrlForType(type: AnimalType): String {
        return ANIMAL_FACT_BASE_URL + type.pathName
    }

    companion object {
        private const val ANIMAL_FACT_BASE_URL = "https://some-random-api.com/animal/"
    }

    inner class TemplateModelClientErrorHandler(private val type: AnimalType) : ResponseErrorHandler {
        override fun hasError(response: ClientHttpResponse): Boolean {
            return response.statusCode.is4xxClientError || response.statusCode.is5xxServerError
        }

        override fun handleError(response: ClientHttpResponse) {
            if (response.statusCode == HttpStatus.NOT_FOUND) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Could not find a fact for $type"
                )
            } else {
                throw ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong with the animal fact API!"
                )
            }
        }
    }
}