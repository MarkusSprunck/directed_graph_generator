package entity

import control.HtmlUtil
import java.util.*
import javax.xml.stream.Location

class Node constructor(name: String = "",
                       nameLong: String = "",
                       cluster: String = "",
                       description: String = "",
                       stereotyp: String = "",
                       location: String = "") {

    var stereotyp = ""
    var name = ""
    var cluster = ""
    var nameLong = ""
    var location = ""
    var description = ""
    var descriptionHtml = ""
    private val depends = HashSet<String>()
    private val dependedOnBy = HashSet<String>()

    private val document: String
        get() {
            val sbDocs = StringBuilder()
            sbDocs.append("<h3>").append(name).append(" - ").append(nameLong).append("</h3>")
            sbDocs.append("<b>Cluster</b>:<br/>").append(cluster).append("<p/>")
            sbDocs.append("<b>Location:</b><br/><em>").append(location).append("<em><p/>")
            sbDocs.append("<b>Status:</b><br/><em>").append(stereotyp).append("<em><p/>")
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
        this.stereotyp = stereotyp
        this.name = name
        this.cluster = cluster
        this.nameLong = nameLong
        this.description = description
        this.descriptionHtml = HtmlUtil.escape(description)
        this.location = location
    }

    fun addDepends(name: String) {
        depends.add("\"" + name + "\"")
    }

    fun addDependedOnBy(name: String) {
        dependedOnBy.add("\"" + name + "\"")
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
