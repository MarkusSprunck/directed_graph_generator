package com.sw.engineering.candies.control

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "server")
data class ServerProperties(
    var port: String = "",
    var host: String = "",
    var url: String = "",
    var baseFolder: String = ""
)