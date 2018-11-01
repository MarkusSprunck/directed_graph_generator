package com.sw.engineering.candies.entity

import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 *
 * This class stores the com.sw.engineering.candies.boundary.main model with all nodes, cluster and links.
 *
 */
class Model {

    private val log = LoggerFactory.getLogger(Model::class.java)

    private val nodes = ConcurrentHashMap<String, Node>()

    private val clusters = ConcurrentHashMap<String, Cluster>()

    fun setNode(name: String, node: Node) {

        // add node
        nodes[name] = node

        // add Cluster if missing
        val clusterName = node.cluster
        if (!clusters.containsKey(key = clusterName)) {
            clusters[clusterName] = Cluster(nodes[name]!!.cluster)
        }
        clusters[clusterName]?.addNode(name, node)
    }

    fun getNode(name: String): Node? = nodes[name]

    fun toJSONStringModel(): String {
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


    fun toPlantUmlModel(clusterName: String = "", showLinks: Boolean = false): String {


        val graphData = StringBuilder()
        graphData.append("\n")

        val linkComments = HashMap<String, String>()

        var index = 0
        for (cluster in clusters.values) {
            val color = Colors.values[index % Colors.values.size]
            log.debug(" > ${cluster.getAllNodes()}  $color")
            graphData.append("skinparam componentBorderColor<<${cluster.getName().toLowerCase()}>> $color\n")
            index++
        }
        graphData.append("\n")

        index = 0
        for (cluster in clusters.values) {

            if ((clusterName == cluster.getName() && clusterName.isNotEmpty()) || clusterName.isEmpty()) {

                for (node in cluster.getAllNodes().values) {
                    if (cluster.getName() != node.name) {

                        for (linkComment in node.linkComments) {
                            linkComments[linkComment.key] = linkComment.value
                        }

                        val stereotype = " <<" + cluster.getName().toLowerCase() + ">> "

                        var newName = " <<" + node.location.toLowerCase() + ">> \\n "
                        newName = newName + " <<" + node.stereotype + ">> \\n \\n"
                        newName = newName + node.nameLong + "\\n "
                        newName = newName + "(" + node.name + ")"

                        graphData.append("[").append(newName).append("]").append(" as ").append(node.name).append(" ").append(stereotype)
                        graphData.append(" [[{")
                                .append(node.description.replace("\n", "\\n")).append("}]]\n")
                    }
                }
            }
            index++

        }

        val linkStrings = HashSet<String>()

        if (showLinks) {
            graphData.append("\n")

            for (node in nodes.values) {
                for (link in node.dependedOnBy) {
                    val linkString = StringBuilder()
                    linkString.append("[").append(node.name).append("]")
                    linkString.append(" ..> ")
                    linkString.append("[").append(link.replace("\"", "")).append("]")
                    linkStrings.add(linkString.toString())
                }
            }
        }

        if (showLinks) {
            for (node in nodes.values) {
                for (link in node.depends) {
                    val linkString = StringBuilder()
                    linkString.append("[").append(link.replace("\"", "")).append("]")
                    linkString.append(" ..> ")
                    linkString.append("[").append(node.name).append("]")
                    linkStrings.add(linkString.toString())
                }
            }
        }
        for (link in linkStrings) {
            graphData.append(link).append(" : " + linkComments[link]).append("\n")
        }

        return graphData.toString()
    }

}