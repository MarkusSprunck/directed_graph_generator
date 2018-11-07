package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Node
import java.util.*

object Model2HTML {

    fun getNode(node: Node): String {

        val result = StringBuilder()

        result.append("<h3>")
        result.append(node.name).append(" - ")
        result.append(node.nameLong).append("</h3>")

        result.append("<b>Cluster</b>:<br/>")
        result.append(node.stereotypeFirst)
        result.append("<p/>")

        result.append("<b>Location:</b><br/><em>")
        result.append(node.stereotypeSecond)
        result.append("<em><p/>")

        result.append("<b>Status:</b><br/><em>")
        result.append(node.stereotypeThird)
        result.append("<em><p/>")

        result.append("<b>Description:</b><br/><em>")
        result.append(HtmlUtil.escape(node.description))
        result.append("<em><p/>")

        // prepare a sorted list of nodes
        val dependsSorted = TreeSet<String>()
        dependsSorted.addAll(node.depends)
        // list all incoming links
        if (!dependsSorted.isEmpty()) {
            // headline is needed
            result.append("<b>Interface from:</b><p/>")
            // list all nodes
            for (temp in dependsSorted) {
                val id = temp.replace("\"", "")
                result.append("<a href=\\\"#obj-$id\\\" ")
                result.append("class=\\\"select-object\\\" ")
                result.append("data-name=\\\"$id\\\">$id</a> ")
            }
            result.append("<p/>")
        }

        // prepare a sorted list of nodes
        val dependedOnBySorted = TreeSet<String>()
        dependedOnBySorted.addAll(node.dependedOnBy)
        // list all outgoing links
        if (!dependedOnBySorted.isEmpty()) {
            // headline is needed
            result.append("<b>Interface to:</b><p/>")
            // list all nodes
            for (temp in dependedOnBySorted) {
                val id = temp.replace("\"", "")
                result.append("<a href=\\\"#obj-$id\\\" ")
                result.append("class=\\\"select-object\\\" ")
                result.append("data-name=\\\"$id\\\">$id</a> ")
            }
            result.append("<p/>")
        }

        return result.toString()
    }

}
