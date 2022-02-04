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
class DiagramGenerator @Autowired constructor(var model2PlantUml: Model2PlantUml) {

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

        val model = ExcelReader.parseExcelSheet(
            excelFileName,
            sourceApplicationPattern,
            strict,
            colorMode,
            showComplex
        )

        if (diagramType == "graph") {

            // Create animated graph
            return FileUtil.load("templates/main.html")
                .replace("##CSS_DATA##", FileUtil.load("static/main.css"))
                .replace("##GEOMETRY_LIB##", FileUtil.load("libs/geometry.js"))
                .replace(
                    "##JQUERRY_SPLITTER_LIB##",
                    FileUtil.load("libs/jquery.splitter.js")
                )
                .replace("##JQUERRY_A_LIB##", FileUtil.load("libs/jquery-1.10.2.min.js"))
                .replace("##JQUERRY_B_LIB##", FileUtil.load("libs/jquery.browser.min.js"))
                .replace("##D3_LIB##", FileUtil.load("libs/d3.v3.min.js"))
                .replace("##JS_CODE##", FileUtil.load("templates/main.js"))
                .replace("##GENERATED_DATA##", Model2JSON.getModel(model))

        } else if (diagramType == "component") {

            // create UML component diagram
            val resultPuml = FileUtil.load("templates/component.puml")
                .replace(
                    "'%PACKAGES%",
                    model2PlantUml.toPlantUmlModel(
                        model,
                        componentName,
                        showLinks,
                        showComplex,
                        colorMode,
                        excelFileName
                    )
                )
                .replace("%DIAGRAM_TITLE%", diagramTitle)

            // Render SVG diagram into stream
            val reader = SourceStringReader(resultPuml)
            val os = ByteArrayOutputStream()
            reader.generateImage(os, FileFormatOption(FileFormat.SVG))
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
            html.append("         ").append(diagramSVG)
            html.append("    </body>")
            html.append("</html>")
            return html.toString()
        }

        return """
                <html>
                    <head>
                        <meta charset=\"utf-8\">
                        <title>DGG</title>
                        <link href='./main.css' rel='stylesheet' type='text/css'>
                    </head>
                    <body>
                        <div>
                            Find code and description of valid URL parameter
                            <a href='https://github.com/MarkusSprunck/directed_graph_generator'>here</a>.
                        </div>
                    </body>
                </html>
                """
    }

}
