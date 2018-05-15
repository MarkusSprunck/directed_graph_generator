package entity

import control.HtmlUtil
import java.util.*

class Node constructor(name: String = "",
                       nameLong: String = "",
                       type: String = "",
                       description: String = "") {

    private var name = ""
    private var nameLong = ""
    private var description = ""
    private var type = ""
    private val depends = HashSet<String>()
    private val dependedOnBy = HashSet<String>()

    private val document: String
        get() {
            val sbDocs = StringBuilder()
            sbDocs.append("<h3>").append(name).append(" - ").append(nameLong).append("</h3>")
            sbDocs.append(type).append("<p/>")
            sbDocs.append("<em>").append(description).append("<em><p/>")

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
        this.name = name
        this.type = type
        this.nameLong = nameLong
        this.description = HtmlUtil.escape(description)
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
        sb.append("    \"name\": \"$nameLong\",\n")
        sb.append("    \"type\": \"$type\",\n")
        sb.append("    \"depends\":" + depends.toString() + ",\n")
        sb.append("    \"dependedOnBy\":" + dependedOnBy.toString() + ",\n")
        sb.append("    \"docs\": \"$document\"\n")
        sb.append("}")
        return sb.toString()
    }


}
