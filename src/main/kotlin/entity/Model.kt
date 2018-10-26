package entity

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

class Model {

    private val log = Logger.getLogger(Model::class.java.name)

    private val nodes = ConcurrentHashMap<String, Node>()
    private val clusters = ConcurrentHashMap<String, Cluster>()

    fun containsNode(name: String) = nodes.containsKey(name)
    fun containsCluster(name: String) = clusters.containsKey(name)

    fun setNode(name: String, node: Node) {

        // add node
        nodes[name] = node

        // add Cluster if missing
        var clusterName = node.cluster
        if (!containsCluster(name = clusterName)) {
            clusters[clusterName] = Cluster(nodes[name]!!.cluster)
        }
        clusters[clusterName]?.addNode(name, node)


    }

    fun setCluster(name: String, cluster: Cluster? = null) {
        if (cluster != null) {
            clusters[name] = cluster
        } else {
            clusters[name] = Cluster(name)
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


    fun toUml(clusterName: String = "", showLinks: Boolean = false): String {



        val graphData = StringBuilder()
        graphData.append("\n")

        val linkComments = HashMap<String, String>()

        var index = 0
        for (cluster in clusters.values) {
            var color = ColorUtil.colors[  index %  ColorUtil.colors.size ]
            log.info("COLOR > ${cluster.name}  $color")
            graphData.append(" skinparam componentBorderColor<<${cluster.name.toLowerCase()}>> $color \n")
            index++
        }
        graphData.append("\n")

        index = 0
        for (cluster in clusters.values) {

            if ((clusterName == cluster.name && clusterName.length > 0) || clusterName.length == 0) {

                for (node in cluster.nodes.values) {
                    if (cluster.name != node.name) {

                        for (linkComment in node.linkComments) {
                            linkComments.set(linkComment.key, linkComment.value)
                        }

                        var stereotype = " <<" + cluster.name.toLowerCase() + ">> "

                        var newName = " <<" + node.location.toLowerCase() + ">> \\n "
                        newName = newName + " <<" + node.stereotyp1 + ">> \\n \\n"
                        newName = newName + node.nameLong + "\\n "
                        newName = newName + "(" + node.name + ")"

                        graphData.append(" [").append(newName).append("]").append(" as ").append(node.name).append(" ").append(stereotype)
                        graphData.append(" [[{")
                                .append(node.description.replace("\n", "\\n")).append("}]]\n")
                    }
                }

                graphData.append("\n")
            }
            index++

        }

        val linkStrings = HashSet<String>()

        if (showLinks) {
            for (node in nodes.values) {
                for (link in node.dependedOnBy) {
                    var linkString = StringBuilder()
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
                    var linkString = StringBuilder()
                    linkString.append("[").append(link.replace("\"", "")).append("]")
                    linkString.append(" ..> ")
                    linkString.append("[").append(node.name).append("]")
                    linkStrings.add(linkString.toString())
                }
            }
        }
        for (link in linkStrings) {
            graphData.append(link).append(" : " + linkComments.get(link)).append("\n")
        }

        return graphData.toString()
    }

}