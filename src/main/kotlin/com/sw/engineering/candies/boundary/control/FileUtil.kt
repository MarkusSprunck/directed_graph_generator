package com.sw.engineering.candies.boundary.control

object FileUtil {

    fun load(name: String): String {
        return javaClass.classLoader.getResource(name).readText()
    }

}