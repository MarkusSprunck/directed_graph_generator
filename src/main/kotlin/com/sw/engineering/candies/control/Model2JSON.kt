package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import com.sw.engineering.candies.entity.Node
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
                graphData.append( nodeToString(node) )
                isFirstElement = false
            }
            graphData.append("}")

            return graphData.toString()
        }


        @JvmStatic
        fun nodeToString(node : Node): String {
            val sb = StringBuilder()
            sb.append("\"").append(node.name).append("\": {\n")
            sb.append("    \"name\": \"").append(node.name).append("\",\n")
            sb.append("    \"type\": \"").append(node.stereotypeFirst).append("\",\n")
            sb.append("    \"depends\":" + node.depends.toString() + ",\n")
            sb.append("    \"dependedOnBy\":" + node.dependedOnBy.toString() + ",\n")
            sb.append("    \"docs\": \"").append(node.document).append("\"\n")
            sb.append("}")
            return sb.toString()
        }

    }
}