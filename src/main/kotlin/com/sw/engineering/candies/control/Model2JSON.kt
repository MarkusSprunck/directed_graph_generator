package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import org.slf4j.LoggerFactory

class Model2JSON {


    companion object {

        private val log = LoggerFactory.getLogger(Model2JSON::class.java)

        @JvmStatic
        fun toJSONStringModel(model : Model): String {
            val graphData = StringBuilder()
            var isFirstElement = true
            graphData.append("{\n")
            for (node in model.getNodes()) {
                graphData.append(if (isFirstElement) "" else ",")
                graphData.append(node.toString())
                isFirstElement = false
            }
            graphData.append("}")

            return graphData.toString()
        }

    }
}