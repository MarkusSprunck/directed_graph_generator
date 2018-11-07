package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import com.sw.engineering.candies.entity.Node
import org.slf4j.LoggerFactory
import java.util.*

class Model2JSON {


    companion object {

        private val log = LoggerFactory.getLogger(Model2JSON::class.java)

        @JvmStatic
        fun toJSONStringModel(model: Model): String {
            val graphData = StringBuilder()
            var isFirstElement = true
            graphData.append("{\n")
            for (node in model.getNodes()) {
                graphData.append(if (isFirstElement) "" else ",")
                graphData.append(nodeToString(node))
                isFirstElement = false
            }
            graphData.append("}")

            return graphData.toString()
        }


        @JvmStatic
        fun nodeToString(node: Node): String {
            val sb = StringBuilder()
            sb.append("\"").append(node.name).append("\": {\n")
            sb.append("    \"name\": \"").append(node.name).append("\",\n")
            sb.append("    \"type\": \"").append(node.stereotypeFirst).append("\",\n")
            sb.append("    \"depends\":" + node.depends.toString() + ",\n")
            sb.append("    \"dependedOnBy\":" + node.dependedOnBy.toString() + ",\n")
            sb.append("    \"docs\": \"").append(nodeToHtmlDocument(node)).append("\"\n")
            sb.append("}")
            return sb.toString()
        }


        @JvmStatic
        fun nodeToHtmlDocument(node: Node): String {

            val sbDocs = StringBuilder()
            sbDocs.append("<h3>").append(node.name).append(" - ").append(node.nameLong).append("</h3>")
            sbDocs.append("<b>Cluster</b>:<br/>").append(node.stereotypeFirst).append("<p/>")
            sbDocs.append("<b>Location:</b><br/><em>").append(node.stereotypeSecond).append("<em><p/>")
            sbDocs.append("<b>Status:</b><br/><em>").append(node.stereotypeThird).append("<em><p/>")
            sbDocs.append("<b>Description:</b><br/><em>").append(node.descriptionHtml).append("<em><p/>")

            if (!node.depends.isEmpty()) {
                sbDocs.append("<b>Interface from:</b><p/>")

                val dependsSorted = TreeSet<String>()
                dependsSorted.addAll(node.depends)

                for (temp in dependsSorted) {
                    val id = temp.replace("\"", "")
                    sbDocs.append("<a href=\\\"#obj-$id\\\" class=\\\"select-object\\\" data-name=\\\"$id\\\">$id</a> ")
                }
                sbDocs.append("<p/>")
            }

            if (!node.dependedOnBy.isEmpty()) {
                sbDocs.append("<b>Interface to:</b><p/>")

                val dependedOnBySorted = TreeSet<String>()
                dependedOnBySorted.addAll(node.dependedOnBy)

                for (temp in dependedOnBySorted) {
                    val id = temp.replace("\"", "")
                    sbDocs.append("<a href=\\\"#obj-$id\\\" class=\\\"select-object\\\" data-name=\\\"$id\\\">$id</a> ")
                }
                sbDocs.append("<p/>")
            }
            return sbDocs.toString()
        }
    }
}