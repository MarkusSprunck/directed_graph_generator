package com.sw.engineering.candies.entity

import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 *
 * This class stores the com.sw.engineering.candies.boundary.main model with all nodes, stereotypeFirst and links.
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
        val clusterName = node.stereotypeFirst
        if (!clusters.containsKey(key = clusterName)) {
            clusters[clusterName] = Cluster(nodes[name]!!.stereotypeFirst)
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


    fun toPlantUmlModel(clusterName: String = "", showLinks: Boolean = false, showComplex : Boolean = true): String {


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

                        var newName = ""
                        if (showComplex) {
                            newName = newName + " << " + node.stereotypeThird.toLowerCase() + ">> \\n "
                            newName = newName + " <<" + node.stereotypeSecond + ">> \\n \\n"
                        }
                        newName = newName + node.nameLong + "\\n "
                        newName = newName + "(" + node.name + ")"

                        val stereotype = " <<" + cluster.getName().toLowerCase() + ">> "
                        graphData.append("[").append(newName).append("]").append(" as ").append(node.name).append(" ")
                        if (showComplex) {
                            graphData.append(stereotype)
                        }
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