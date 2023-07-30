package com.sw.engineering.candies.entity

import java.util.concurrent.ConcurrentHashMap

/**
 * This class stores a named Cluster with all containing
 * nodes of this specific Cluster.
 */
class Cluster(name: String) {

    // store all nodes of the cluster
    private var nodes = ConcurrentHashMap<String, Node>()

    // name of cluster
    private var name = ""

    init {
        this.name = name
    }

    fun addNode(name: String, node: Node) {
        nodes[name] = node
    }

    fun getNodes(): MutableList<Node> {
        return nodes.values.toMutableList()
    }

    fun getName(): String {
        return name
    }

}
