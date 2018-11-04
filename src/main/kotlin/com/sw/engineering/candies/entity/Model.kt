package com.sw.engineering.candies.entity

import java.util.concurrent.ConcurrentHashMap

/**
 * This class stores the main model with all nodes and clusters.
 * The link information is stored in the nodes. It generates the
 * data structures for visualizations.
 */
class Model {

    private val nodes = ConcurrentHashMap<String, Node>()

    private val clusters = ConcurrentHashMap<String, Cluster>()

    private fun insertIntoListOfNodes(name: String, node: Node) {
        nodes[name] = node
    }

    private fun insertIntoListOfClusters(node: Node, name: String) {
        val clusterName = node.stereotypeFirst
        if (!clusters.containsKey(key = clusterName)) {
            clusters[clusterName] = Cluster(nodes[name]!!.stereotypeFirst)
        }
        clusters[clusterName]?.addNode(name, node)
    }

    fun setNode(name: String, node: Node) {
        insertIntoListOfNodes(name, node)
        insertIntoListOfClusters(node, name)
    }

    fun getNode(name: String): Node? = nodes[name]

    fun getNodes(): MutableCollection<Node> = nodes.values.toMutableList()

    fun getClusters(): MutableCollection<Cluster> = clusters.values.toMutableList()

}