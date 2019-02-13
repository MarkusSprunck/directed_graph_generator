package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import java.util.*



object Model2PlantUml {

    private val log = LoggerFactory.getLogger(Model2PlantUml::class.java)

    @Value("\${server.port}")
    var port = 8080

    @Value("\${server.host}")
    var host = "localhost"

    fun toPlantUmlModel(model: Model,
                        clusterName: String = "", showLinks: Boolean = false,
                        showComplex: Boolean = true,
                        colorMode: String = "location",
                        excelFileName: String = "data.xlsx"): String {
        val graphData = StringBuilder()
        graphData.append("\n")

        val linkComments = HashMap<String, String>()

        var index = 0
        for (cluster in model.getClusters()) {
            val color = Colors.values[index % Colors.values.size]
            log.debug(" > ${cluster.getNodes()}  $color")
            graphData.append("skinparam componentBorderColor<<${cluster.getName().toLowerCase()}>> $color\n")
            index++
        }
        graphData.append("\n")

        index = 0
        val allNodes = mutableSetOf<String>()
        for (cluster in model.getClusters()) {

            if ((clusterName == cluster.getName() && clusterName.isNotEmpty()) || clusterName.isEmpty()) {

                for (node in cluster.getNodes()) {
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
                            allNodes.add(node.name)
                        }
                        graphData.append(" [[" ).append(createLink(node.name, showComplex, colorMode, excelFileName))
                                .append("{")
                                .append(node.description.replace("\n", "\\n")).append("}]]\n")
                    }
                }
            }
            index++

        }

        val linkStrings = HashSet<String>()

        if (showLinks) {
            graphData.append("\n")

            for (node in model.getNodes()) {
                for (link in node.dependedOnBy) {
                    val linkString = StringBuilder()
                    linkString.append("[").append(node.name).append("]")
                    linkString.append(" ..> ")
                    val targetName = link.replace("\"", "")
                    if (!allNodes.contains( targetName)) {
                        graphData.append("[").append(targetName).append("] [[")
                                .append(createLink(targetName, showComplex, colorMode, excelFileName)).append(" {}]]\n")
                    }
                    linkString.append("[").append(targetName).append("]")
                    linkStrings.add(linkString.toString())
                }
            }
        }

        if (showLinks) {
            for (node in model.getNodes()) {
                for (link in node.depends) {
                    val linkString = StringBuilder()
                    val sourceName = link.replace("\"", "")
                    if (!allNodes.contains( sourceName)) {
                        graphData.append("[").append(sourceName).append("] [[")
                                .append(createLink(sourceName, showComplex, colorMode, excelFileName)).append(" {}]] \n")
                    }
                    linkString.append("[").append(sourceName).append("]")
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

    private fun createLink(name: String, showComplex: Boolean, colorMode : String, excelFileName: String) =
            "http://" + host +
                    ":" + port +
                    "/diagram?type=component" +
                    "&file=" +excelFileName +
                    "&strict=false"+
                    "&diagramTitle=UML-Component" +
                    "&showLinks=true" +
                    "&showComplex="+showComplex +
                    "&colorMode=" + colorMode +
                    "&filter=" + name

}
