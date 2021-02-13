package com.sw.engineering.candies

import com.sw.engineering.candies.control.MainApplication
import com.sw.engineering.candies.control.ServerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ServerProperties::class)
class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<MainApplication>(*args) {
                System.setProperty("version.kotlin", KotlinVersion.CURRENT.toString())
            }
        }
    }

}
