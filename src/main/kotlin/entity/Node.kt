package entity

import control.HtmlUtil
import java.util.*

class Node constructor(name: String = "",
                       nameLong: String = "",
                       description: String = "",
                       stereotyp0: String = "",
                       stereotyp1: String = "",
                       stereotyp2: String = "") {

    var stereotyp1 = ""
    var name = ""
    var cluster = ""
    var nameLong = ""
    var location = ""
    var description = ""
    var descriptionHtml = ""
    val depends = HashSet<String>()
    val dependedOnBy = HashSet<String>()
    val linkComments = HashMap<String, String>()

    private val document: String
        get() {
            val sbDocs = StringBuilder()
            sbDocs.append("<h3>").append(name).append(" - ").append(nameLong).append("</h3>")
            sbDocs.append("<b>Cluster</b>:<br/>").append(cluster).append("<p/>")
            sbDocs.append("<b>Location:</b><br/><em>").append(location).append("<em><p/>")
            sbDocs.append("<b>Status:</b><br/><em>").append(stereotyp1).append("<em><p/>")
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

    init {
        this.stereotyp1 = stereotyp1
        this.name = name
        this.cluster = stereotyp0
        this.nameLong = nameLong
        this.description = description
        this.descriptionHtml = HtmlUtil.escape(description)
        this.location = stereotyp2
    }

    fun addDepends(name: String, comment: String = "") {
        if (!depends.contains(name)) {
            depends.add("\"" + name + "\"")
            linkComments.set("[" + name + "] ..> [" + this.name + "]", comment)
        }
    }

    fun addDependedOnBy(name: String, comment: String = "") {
        if (!dependedOnBy.contains(name)) {
            dependedOnBy.add("\"" + name + "\"")
            linkComments.set("[" + this.name + "] ..> [" + name + "]", comment)
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("\"$name\": {\n")
        sb.append("    \"name\": \"$name\",\n")
        sb.append("    \"type\": \"$cluster\",\n")
        sb.append("    \"depends\":" + depends.toString() + ",\n")
        sb.append("    \"dependedOnBy\":" + dependedOnBy.toString() + ",\n")
        sb.append("    \"docs\": \"$document\"\n")
        sb.append("}")
        return sb.toString()
    }


}
