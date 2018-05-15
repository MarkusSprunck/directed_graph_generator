package control

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages =  ["control", "boundary", "entity"])
class MainApplication {
}

class GlobalProperties {
    var poolSize: Int = 5
}

@Configuration
@PropertySource("\${external.config.file}")
class DGGConfiguration {

    @Bean
    @ConfigurationProperties("dgg")
    fun globalProperties() = GlobalProperties()

    @Bean
    fun threadPoolTaskScheduler() = ThreadPoolTaskScheduler().apply {
        poolSize = globalProperties().poolSize
    }
}

