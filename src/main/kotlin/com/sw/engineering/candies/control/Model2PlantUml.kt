package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger.getLogger


@Component
class Model2PlantUml @Autowired constructor(private val serverProperties: ServerProperties) {

    private val log = getLogger(Model2PlantUml::class.java.typeName)

    fun toPlantUmlModel(
        model: Model,
        clusterName: String = "",
        showLinks: Boolean = false,
        showComplex: Boolean = true,
        colorMode: String = "location",
        excelFileName: String = "data.xlsx"
    ): String {
        val graphData = StringBuilder()
        graphData.append("\n")

        val linkComments = HashMap<String, String>()

        var index = 0
        for (cluster in model.getClusters()) {
            val color = Colors.values[index % Colors.values.size]
            log.log(Level.FINE, " > ${cluster.getNodes()}  $color")
            graphData.append(
                "skinparam componentBorderColor<<${
                    cluster.getName().lowercase(Locale.getDefault())
                }>> $color\n"
            )
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
                            newName =
                                newName + " << " + node.stereotypeThird.lowercase(Locale.getDefault()) + ">> \\n "
                            newName =
                                newName + " <<" + node.stereotypeSecond + ">> \\n \\n"
                        }
                        newName = newName + node.nameLong + "\\n "
                        newName = newName + "(" + node.name + ")"

                        val stereotype = " <<" + cluster.getName().lowercase(Locale.getDefault()) + ">> "
                        graphData.append("[").append(newName).append("]").append(" as ")
                            .append(node.name).append(" ")
                        if (showComplex) {
                            graphData.append(stereotype)
                            allNodes.add(node.name)
                        }
                        graphData.append(" [[").append(
                            createLink(
                                node.name,
                                showComplex,
                                colorMode,
                                excelFileName
                            )
                        ).append("{").append(node.description.replace("\n", "\\n"))
                            .append("}]]\n")
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
                    if (!allNodes.contains(targetName)) {
                        graphData.append("[").append(targetName).append("] [[")
                            .append(
                                createLink(
                                    targetName,
                                    showComplex,
                                    colorMode,
                                    excelFileName
                                )
                            ).append(" {}]]\n")
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
                    if (!allNodes.contains(sourceName)) {
                        graphData.append("[").append(sourceName).append("] [[")
                            .append(
                                createLink(
                                    sourceName,
                                    showComplex,
                                    colorMode,
                                    excelFileName
                                )
                            ).append(" {}]] \n")
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

    private fun createLink(
        name: String,
        showComplex: Boolean,
        colorMode: String,
        excelFileName: String
    ) =
        serverProperties.url +
                "/diagram?type=component" +
                "&file=" + excelFileName +
                "&strict=false" +
                "&title=UML-Component-Subview" +
                "&showLinks=true" +
                "&showComplex=" + showComplex +
                "&colorMode=" + colorMode +
                "&filter=" + name

}
