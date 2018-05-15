package control

import java.io.IOException
import java.util.logging.Logger

object DiagramGenerator {

    private val LOGGER = Logger.getLogger(DiagramGenerator::class.java.name)

    @Throws(IOException::class)
    fun run(excelFileName: String, sourceApplicationPattern: String): String {
        LOGGER.info("### START ###")
        val model = ExcelReader.parseExcelSheet(excelFileName, sourceApplicationPattern)
        val resultHtml = FileUtil.load("templates/main.html")
                .replace("##CSS_DATA##", FileUtil.load("static/main.css"))
                .replace("##GEOMETRY_LIB##", FileUtil.load("libs/geometry.js"))
                .replace("##JQUERRY_A_LIB##", FileUtil.load("libs/jquery-1.10.2.min.js"))
                .replace("##JQUERRY_B_LIB##", FileUtil.load("libs/jquery.browser.min.js"))
                .replace("##D3_LIB##", FileUtil.load("libs/d3.v3.min.js"))
                .replace("##JS_CODE##", FileUtil.load("templates/main.js"))
                .replace("##GENERATED_DATA##", model.toString())
        LOGGER.info("###  END  ###")
        return resultHtml
    }


}
