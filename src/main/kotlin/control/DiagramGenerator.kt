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
                .replace("##MY_CSS_DATA##",      FileUtil.load("static/main.css"))
                .replace("##MY_GEOMETRY_LIB##",  FileUtil.load("libs/geometry.js"))
                .replace("##MY_JQUERRY_A_LIB##", FileUtil.load("libs/jquery-1.10.2.min.js"))
                .replace("##MY_JQUERRY_B_LIB##", FileUtil.load("libs/jquery.browser.min.js"))
                .replace("##MY_D3_LIB##",        FileUtil.load("libs/d3.v3.min.js"))
                .replace("##MY_JS_CODE##",       FileUtil.load("templates/main.js"))
                .replace("##MY_GENERATED_DATA##", model.toString())
        LOGGER.info("###  END  ###")
        return resultHtml
    }


}
