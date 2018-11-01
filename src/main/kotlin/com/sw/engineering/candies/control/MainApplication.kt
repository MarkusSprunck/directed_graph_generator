package com.sw.engineering.candies.control

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class, ThymeleafAutoConfiguration::class])
@ComponentScan(basePackages = ["com.sw.engineering.candies"], lazyInit = true)
class MainApplication

