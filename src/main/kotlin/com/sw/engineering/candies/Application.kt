package com.sw.engineering.candies

import com.sw.engineering.candies.control.MainApplication
import org.springframework.boot.runApplication


class Application {

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            runApplication<MainApplication>(*args) {
                System.setProperty("version.kotlin", KotlinVersion.CURRENT.toString())
            }
        }
    }

}
