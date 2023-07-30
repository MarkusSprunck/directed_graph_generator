package com.sw.engineering.candies.control

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.charset.Charset

@Component
class DiagramGenerator @Autowired constructor(
    var model2PlantUml: Model2PlantUml,
    var excelReader: ExcelReader
) {

    @Throws(IOException::class)
    fun run(
        excelFileName: String,
        sourceApplicationPattern: String,
        diagramType: String,
        componentName: String,
        showLinks: Boolean,
        showComplex: Boolean,
        strict: Boolean,
        colorMode: String,
        diagramTitle: String
    ): String {

        val model = excelReader.parseExcelSheet(
            excelFileName,
            sourceApplicationPattern,
            strict,
            colorMode,
            showComplex
        )

        // create UML component diagram
        val resultPuml: String;

        if (diagramTitle.isEmpty()) {
            resultPuml = FileUtil.load("templates/component_without_title.puml")
                .replace(
                    "'%PACKAGES%",
                    model2PlantUml.toPlantUmlModel(model, componentName, showLinks, showComplex, colorMode, excelFileName)
                )
        } else {
            resultPuml = FileUtil.load("templates/component_with_title.puml")
                .replace(
                    "'%PACKAGES%",
                    model2PlantUml.toPlantUmlModel(model, componentName, showLinks, showComplex, colorMode, excelFileName)
                )
                .replace("%DIAGRAM_TITLE%", diagramTitle)
        }
        // Render SVG diagram into stream
        val reader = SourceStringReader(resultPuml)
        val os = ByteArrayOutputStream()
        reader.outputImage(os, FileFormatOption(FileFormat.SVG)).description
        os.close()
        val diagramSVG = String(os.toByteArray(), Charset.forName("UTF-8"))

        // Create html page with embedded diagram
        val html = StringBuilder()
        html.append("<html>")
        html.append("    <head>")
        html.append("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">")
        html.append("        <meta charset=\"utf-8\">")
        html.append("        <title>UML Components</title>")
        html.append("        <link href=\"./main.css\" rel=\"stylesheet\" type=\"text/css\">\n")
        html.append("    </head>")
        html.append("    <body>")
        html.append("       <div>").append(diagramSVG).append("</div>")
        html.append("    </body>")
        html.append("</html>")
        return html.toString()
    }

}
