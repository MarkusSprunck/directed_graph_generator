package com.sw.engineering.candies

import com.sw.engineering.candies.control.ServerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class, ThymeleafAutoConfiguration::class])
@EnableConfigurationProperties(ServerProperties::class)
@Configuration
@ComponentScan(basePackages = ["com.sw.engineering.candies"], lazyInit = true)
class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Application>(*args) {
                System.setProperty("version.kotlin", KotlinVersion.CURRENT.toString())
            }
        }
    }

}
