package com.sw_engineering_candies.boundary.control

object FileUtil {

    fun load(name: String): String {
        return javaClass.classLoader.getResource(name).readText()
    }

}