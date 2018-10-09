package control

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.logging.Logger

object DiagramGenerator {

    private val log = Logger.getLogger(DiagramGenerator::class.java.name)

    @Throws(IOException::class)
    fun run(excelFileName: String, sourceApplicationPattern: String, diagramType: String, componentName: String): String {
        log.info("### START ###")
        val model = ExcelReader.parseExcelSheet(excelFileName, sourceApplicationPattern)

        if (diagramType == "graph") {
            val resultHtml = FileUtil.load("templates/main.html")
                    .replace("##CSS_DATA##", FileUtil.load("static/main.css"))
                    .replace("##GEOMETRY_LIB##", FileUtil.load("libs/geometry.js"))
                    .replace("##JQUERRY_SPLITTER_LIB##", FileUtil.load("libs/jquery.splitter.js"))
                    .replace("##JQUERRY_A_LIB##", FileUtil.load("libs/jquery-1.10.2.min.js"))
                    .replace("##JQUERRY_B_LIB##", FileUtil.load("libs/jquery.browser.min.js"))
                    .replace("##D3_LIB##", FileUtil.load("libs/d3.v3.min.js"))
                    .replace("##JS_CODE##", FileUtil.load("templates/main.js"))
                    .replace("##GENERATED_DATA##", model.toString())
            log.info("###  END OK ###")
            return resultHtml
        }

        if (diagramType == "component") {

            val resultPuml = FileUtil.load("component.puml")
                    .replace("'##PACKAGES##", model.toUml(componentName))

            log.info(resultPuml)

            val reader = SourceStringReader(resultPuml)
            val os = ByteArrayOutputStream()
            reader.outputImage(os, FileFormatOption(FileFormat.SVG))
            os.close()

            // The XML is stored into svg
            var svg = StringBuilder()
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


            log.info("###  END OK ###")
            return svg.toString()
        }

        log.info("###  END NOK ###")
        return "<h3>No valid URL parameter for type, values would be 'graph' or 'component'</h3>"
    }


}
