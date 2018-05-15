package entity

import java.util.concurrent.ConcurrentHashMap

class Model {

    private val nodes = ConcurrentHashMap<String, Node>()

    fun containsNode(name: String) = nodes.containsKey(name)

    fun setNode(name: String, node: Node? = null) {
        if (node != null) {
            nodes[name] = node
        } else {
            nodes[name] = Node(  name )
        }
    }

    fun getNode(name: String): Node? = nodes[name]

    override fun toString(): String {
        val graphData = StringBuilder()
        var isFirstElement = true
        graphData.append("{\n")
        for (node in nodes.values) {
            graphData.append(if (isFirstElement) "" else ",")
            graphData.append(node.toString())
            isFirstElement = false
        }
        graphData.append("}")

        return graphData.toString()
    }

}