package com.wongislandd.springtemplate.transformer

import com.wongislandd.springtemplate.models.ExternalAnimalFact
import com.wongislandd.springtemplate.models.InternalAnimalFact
import com.wongislandd.springtemplate.utils.Transformer
import org.springframework.stereotype.Service

@Service
class AnimalFactTransformer: Transformer<ExternalAnimalFact, InternalAnimalFact> {

    override fun transform(input: ExternalAnimalFact): InternalAnimalFact {
        return InternalAnimalFact(input.fact)
    }

}