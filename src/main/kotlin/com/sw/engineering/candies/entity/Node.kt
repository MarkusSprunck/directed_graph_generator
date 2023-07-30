package com.sw.engineering.candies.entity

/**
 *
 * This class stores all information of a single node.
 *
 */
class Node(
    name: String,
    nameLong: String,
    description: String,
    stereotypeFirst: String,
    stereotypeSecond: String,
    stereotypeThird: String
) {

    // properties of this Node
    internal var name = ""
    internal var nameLong = ""
    internal var description = ""
    internal var stereotypeFirst = ""
    internal var stereotypeThird = ""
    internal var stereotypeSecond = ""

    // store names of ingoing links
    internal val depends = HashSet<String>()

    // store names of outgoing links
    internal val dependedOnBy = HashSet<String>()

    // store link comment for depend and/or depending nodes
    internal val linkComments = HashMap<String, String>()

    init {
        this.name = name
        this.nameLong = nameLong
        this.description = description
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
