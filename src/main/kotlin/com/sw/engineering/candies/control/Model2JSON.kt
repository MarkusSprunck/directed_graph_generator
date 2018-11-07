package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import com.sw.engineering.candies.entity.Node

object Model2JSON {

    fun getModel(model: Model): String {
        val graphData = StringBuilder()
        var isFirstElement = true
        graphData.append("{\n")
        for (node in model.getNodes()) {
            if (!isFirstElement) {
                graphData.append(",")
            }
            graphData.append(getNode(node))
            isFirstElement = false
        }
        graphData.append("}")

        return graphData.toString()
    }


    internal fun getNode(node: Node): String {
        val sb = StringBuilder()
        sb.append("\"").append(node.name).append("\": {\n")
        sb.append("    \"name\": \"").append(node.name).append("\",\n")
        sb.append("    \"type\": \"").append(node.stereotypeFirst).append("\",\n")
        sb.append("    \"depends\":" + node.depends.toString() + ",\n")
        sb.append("    \"dependedOnBy\":" + node.dependedOnBy.toString() + ",\n")
        sb.append("    \"docs\": \"").append(Model2HTML.getNode(node)).append("\"\n")
        sb.append("}")
        return sb.toString()
    }

}