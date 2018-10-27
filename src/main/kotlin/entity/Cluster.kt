package entity

import java.util.concurrent.ConcurrentHashMap

class Cluster constructor(name: String = "") {

    val nodes = ConcurrentHashMap<String, Node>()

   // fun containsNode(name: String) = nodes.containsKey(name)

    var name = ""

    init {
        this.name = name
    }


    override fun toString(): String {
        return name
    }

    fun addNode(name: String, node: Node) {
        nodes[name] = node
    }

}
