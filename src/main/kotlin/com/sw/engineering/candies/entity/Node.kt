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
    internal val depends = HashSet<String>()

    // outgoing links
    internal val dependedOnBy = HashSet<String>()

    // Link comment for depend and/or depending nodes
    internal val linkComments = HashMap<String, String>()

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


}
