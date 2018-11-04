package com.sw.engineering.candies.control

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.charset.Charset

object DiagramGenerator {

    @Throws(IOException::class)
    fun run(excelFileName: String,
            sourceApplicationPattern: String,
            diagramType: String,
            componentName: String,
            showLinks: Boolean,
            showComplex: Boolean,
            strict: Boolean,
            colorMode: String): String {

        val model = ExcelReader.parseExcelSheet(excelFileName, sourceApplicationPattern, strict, colorMode, showComplex)

        var result = "<h3>Enter valid URL parameter for type {'graph', 'component'}</h3>"

        if (diagramType == "graph") {
            result = FileUtil.load("templates/main.html")
                    .replace("##CSS_DATA##", FileUtil.load("static/main.css"))
                    .replace("##GEOMETRY_LIB##", FileUtil.load("libs/geometry.js"))
                    .replace("##JQUERRY_SPLITTER_LIB##", FileUtil.load("libs/jquery.splitter.js"))
                    .replace("##JQUERRY_A_LIB##", FileUtil.load("libs/jquery-1.10.2.min.js"))
                    .replace("##JQUERRY_B_LIB##", FileUtil.load("libs/jquery.browser.min.js"))
                    .replace("##D3_LIB##", FileUtil.load("libs/d3.v3.min.js"))
                    .replace("##JS_CODE##", FileUtil.load("templates/main.js"))
                    .replace("##GENERATED_DATA##", Model2JSON.toJSONStringModel(model))
        }

        if (diagramType == "component") {

            val resultPuml = FileUtil.load("templates/component.puml")
                    .replace("'##PACKAGES##", Model2PlantUml.toPlantUmlModel(model, componentName, showLinks, showComplex))

            // Uncomment just for debugging
            // File("output/components.puml").writeText(resultPuml)

            // Render SVG diagram
            val reader = SourceStringReader(resultPuml)
            val os = ByteArrayOutputStream()
            reader.outputImage(os, FileFormatOption(FileFormat.SVG))
            os.close()

            // The XML is stored into svg
            val svg = StringBuilder()
            svg.append("<html>")
            svg.append("    <head>")
            svg.append("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">")
            svg.append("        <meta charset=\"utf-8\">")
            svg.append("        <title>UML Components</title>")
            svg.append("    </head>")
            svg.append("    <body>")
            svg.append("        " + String(os.toByteArray(), Charset.forName("UTF-8")))
            svg.append("    </body>")
            svg.append("</html>")

            result = svg.toString()
        }

        return result
    }


}
