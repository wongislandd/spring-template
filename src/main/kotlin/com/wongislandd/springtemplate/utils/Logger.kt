package com.wongislandd.springtemplate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory


open class Logger {

    val LOGGER: Logger = LoggerFactory.getLogger(this::class.java.simpleName)

}