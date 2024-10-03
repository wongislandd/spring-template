package com.wongislandd.springtemplate.resources

import com.wongislandd.springtemplate.models.AnimalType
import com.wongislandd.springtemplate.services.AnimalFactService
import com.wongislandd.springtemplate.utils.Logger
import org.springframework.web.bind.annotation.*

/**
 * Expose an entry point
 */
@RestController
@RequestMapping("/animalFacts")
class AnimalFactResource(private val animalFactService: AnimalFactService) : Logger() {

    @GetMapping
    @CrossOrigin
    fun getAnimalFact(): String {
        return animalFactService.getAnimalFact(AnimalType.entries.random())
    }

    @GetMapping("/getByAnimal")
    @CrossOrigin
    fun getAnimalFactByType(@RequestParam animal: AnimalType): String {
        return animalFactService.getAnimalFact(animal)
    }
}