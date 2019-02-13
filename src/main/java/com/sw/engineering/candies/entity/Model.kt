package com.sw.engineering.candies.entity

import java.util.concurrent.ConcurrentHashMap

/**
 * This class stores the main model with all nodes and clusters.
 * The link information is stored in the nodes.
 */
class Model {

    // store all nodes of the model
    private val nodes = ConcurrentHashMap<String, Node>()

    // store named cluster in the model
    private val clusters = ConcurrentHashMap<String, Cluster>()

    fun setNode(name: String, node: Node) {
        insertIntoListOfNodes(name, node)
        insertIntoListOfClusters(node, name)
    }

    private fun insertIntoListOfNodes(nodeName: String, node: Node) {
        nodes[nodeName] = node
    }

    private fun insertIntoListOfClusters(node: Node, name: String) {
        val clusterName = node.stereotypeFirst
        if (!clusters.containsKey(key = clusterName)) {
            clusters[clusterName] = Cluster(nodes[name]!!.stereotypeFirst)
        }
        clusters[clusterName]?.addNode(name, node)
    }

    fun getNode(name: String): Node? = nodes[name]

    fun getNodes(): MutableCollection<Node> = nodes.values.toMutableList()

    fun getClusters(): MutableCollection<Cluster> = clusters.values.toMutableList()

}