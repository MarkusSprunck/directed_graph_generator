package com.sw.engineering.candies.entity

import com.sw.engineering.candies.control.HtmlUtil
import java.util.*

/**
 *
 * This class stores all information of a single node.
 *
 */
class Node constructor(name: String,
                       nameLong: String,
                       description: String,
                       stereotypeFirst: String,
                       stereotypeSecond: String,
                       stereotypeThird: String ) {

    // properties of the node
    internal var name = ""
    internal var nameLong = ""
    internal var stereotypeFirst = ""
    internal var stereotypeThird = ""
    internal var stereotypeSecond = ""
    internal var description = ""

    // generated HTML for animated graph
    internal var descriptionHtml = ""

    // ingoing links
    val depends = HashSet<String>()

    // outgoing links
    val dependedOnBy = HashSet<String>()

    // Link comment for depend and/or depending nodes
    val linkComments = HashMap<String, String>()

    init {
        this.name = name
        this.nameLong = nameLong
        this.description = description
        this.descriptionHtml = HtmlUtil.escape(description)
        this.stereotypeFirst = stereotypeFirst
        this.stereotypeSecond = stereotypeSecond
        this.stereotypeThird = stereotypeThird
    }

    fun addDepends(name: String, comment: String = "") {
        if (!depends.contains(name)) {
            depends.add("\"" + name + "\"")
            linkComments["[" + name + "] ..> [" + this.name + "]"] = comment
        }
    }

    fun addDependedOnBy(name: String, comment: String = "") {
        if (!dependedOnBy.contains(name)) {
            dependedOnBy.add("\"" + name + "\"")
            linkComments["[" + this.name + "] ..> [" + name + "]"] = comment
        }
    }

    // this is just needed for the animated directed graph
    internal val document: String
        get() {
            val sbDocs = StringBuilder()
            sbDocs.append("<h3>").append(name).append(" - ").append(nameLong).append("</h3>")
            sbDocs.append("<b>Cluster</b>:<br/>").append(stereotypeFirst).append("<p/>")
            sbDocs.append("<b>Location:</b><br/><em>").append(stereotypeSecond).append("<em><p/>")
            sbDocs.append("<b>Status:</b><br/><em>").append(stereotypeThird).append("<em><p/>")
            sbDocs.append("<b>Description:</b><br/><em>").append(descriptionHtml).append("<em><p/>")

            if (!depends.isEmpty()) {
                sbDocs.append("<b>Interface from:</b><p/>")

                val dependsSorted = TreeSet<String>()
                dependsSorted.addAll(depends)

                for (temp in dependsSorted) {
                    val id = temp.replace("\"", "")
                    sbDocs.append("<a href=\\\"#obj-$id\\\" class=\\\"select-object\\\" data-name=\\\"$id\\\">$id</a> ")
                }
                sbDocs.append("<p/>")
            }

            if (!dependedOnBy.isEmpty()) {
                sbDocs.append("<b>Interface to:</b><p/>")

                val dependedOnBySorted = TreeSet<String>()
                dependedOnBySorted.addAll(dependedOnBy)

                for (temp in dependedOnBySorted) {
                    val id = temp.replace("\"", "")
                    sbDocs.append("<a href=\\\"#obj-$id\\\" class=\\\"select-object\\\" data-name=\\\"$id\\\">$id</a> ")
                }
                sbDocs.append("<p/>")
            }
            return sbDocs.toString()
        }

}
