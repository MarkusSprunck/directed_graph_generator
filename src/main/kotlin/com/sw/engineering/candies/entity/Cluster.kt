package com.sw.engineering.candies.entity

import java.util.concurrent.ConcurrentHashMap

/**
 *
 * This class stores a named Cluster with all containing nodes.
 *
 */
class Cluster constructor(name: String) {

    private var nodes = ConcurrentHashMap<String, Node>()

    private var name = ""

    init {
        this.name = name
    }

    fun addNode(name: String, node: Node) {
        nodes[name] = node
    }

    fun getAllNodes(): ConcurrentHashMap<String, Node> {
        return ConcurrentHashMap(nodes)
    }

    fun getName(): String {
        return name
    }

}
