package com.sw.engineering.candies

import com.sw.engineering.candies.control.MainApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {

    runApplication<MainApplication>(*args) {
        System.setProperty("kotlin.version", KotlinVersion.CURRENT.toString())
    }

}