package com.morosystems.kotlinrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinRestApplication

fun main(args: Array<String>) {
    runApplication<KotlinRestApplication>(*args)
}
