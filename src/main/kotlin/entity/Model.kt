package entity

import control.DiagramGenerator
import java.util.concurrent.ConcurrentHashMap

class Model {

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


    fun toUml(clusterName: String = ""): String {

        val colors = arrayOf(
                "#ff4000",
                "#ff8000",
                "#ffff00",
                "#40ff00",
                "#00ffbf",
                "#00bfff",
                "#0040ff",
                "#8000ff",
                "#bf00ff",
                "#ff00bf",
                "#ff0040"
        )

        val graphData = StringBuilder()
        graphData.append("\n")
        var index = 0

        var isPackages = false
        for (cluster in clusters.values) {

            if ((clusterName == cluster.name && clusterName.length > 0) || clusterName.length == 0 ) {

                if (isPackages) {
                    graphData.append("package \"")
                    graphData.append(cluster.name)
                    graphData.append("\" ").append(colors[index % colors.size]).append(" {").append("\n")
                }
                for (node in cluster.nodes.values) {
                    if (cluster.name != node.name) {

                        var stereotype =   " <<" + cluster.name.toLowerCase() + ">> "

                        var newName =  " <<" + node.location.toLowerCase() + ">> \\n "
                        newName = newName + " <<" + node.stereotyp + ">> \\n \\n"
                        newName = newName + node.nameLong + "\\n "
                        newName = newName + "(" + node.name + ")"

                        graphData.append(" [").append(newName).append("]").append(stereotype)
                        graphData.append(" [[{")
                                .append(node.description.replace("\n", "\\n")).append("}]]\n")
                    }
                }

                if (isPackages) {
                    graphData.append("}")
                    graphData.append("\n")
                }
                graphData.append("\n")
            }
            index++

        }
        return graphData.toString()
    }

}