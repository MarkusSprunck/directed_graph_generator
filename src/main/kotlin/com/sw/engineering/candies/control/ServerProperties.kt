package com.sw.engineering.candies.control

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("server")
data class ServerProperties(var port: String, val host: String, var url: String)
