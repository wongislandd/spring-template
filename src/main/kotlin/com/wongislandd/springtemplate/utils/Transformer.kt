package com.wongislandd.springtemplate.utils

interface Transformer<X,Y> {
    fun transform(input: X): Y
}