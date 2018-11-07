package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Node
import java.util.*

class Model2HTML {

    companion object {

        @JvmStatic
        fun getNode(node: Node): String {

            val sbDocs = StringBuilder()
            sbDocs.append("<h3>").append(node.name).append(" - ").append(node.nameLong).append("</h3>")
            sbDocs.append("<b>Cluster</b>:<br/>").append(node.stereotypeFirst).append("<p/>")
            sbDocs.append("<b>Location:</b><br/><em>").append(node.stereotypeSecond).append("<em><p/>")
            sbDocs.append("<b>Status:</b><br/><em>").append(node.stereotypeThird).append("<em><p/>")
            sbDocs.append("<b>Description:</b><br/><em>").append(HtmlUtil.escape(node.description)).append("<em><p/>")

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
