package com.wongislandd.springtemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/*
 * A template spring server, using animal facts to help understand
 * the way everything plugs in together.
 */
@SpringBootApplication
class SpringTemplateApplication

fun main(args: Array<String>) {
	runApplication<SpringTemplateApplication>(*args)
}
