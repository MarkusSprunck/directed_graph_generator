package entity

import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

class Model {

    private val log = Logger.getLogger(Model::class.java.name)

    private val nodes = ConcurrentHashMap<String, Node>()

    fun isNodeExisting(name: String) = nodes.containsKey(name)

    fun addNode(name: String, node: Node? = null) =
            nodes.set(name, node ?: Node("", "", "", ""))

    fun getNode(name: String): Node? = nodes.get(name)

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