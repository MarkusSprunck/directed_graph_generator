package control

import entity.Model
import entity.Node
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.util.logging.Logger
import java.util.regex.Pattern

object ExcelReader {

    private val log = Logger.getLogger(ExcelReader::class.java.name)

    fun parseExcelSheet(excelFileName: String, sourceApplicationPattern: String): Model {
        log.info("SOURCE_PATTERN = '$sourceApplicationPattern'")
        val result = Model()


        val sh = openSheet(excelFileName, "Applications")
        if (sh == null) {
            log.info("*** SKIP PROCESSING APPLICATIONS - UNABLE TO OPEN SHEET $excelFileName")
            return result
        }
        parseNodes(sh, sourceApplicationPattern, result)



        val sh2 = openSheet(excelFileName, "Links")
        if (sh2 == null) {
            log.info("*** SKIP PROCESSING LINKS - UNABLE TO OPEN SHEET $excelFileName")
            return result
        }
        parseLinks(sh2, sourceApplicationPattern, result)

        return result
    }

    private fun parseLinks(sheet: XSSFSheet, sourceApplicationPattern: String, result: Model) {
        log.info("PROCESS LINKS")
        for (currentRow in 2..sheet.lastRowNum) {
            val row = sheet.getRow(currentRow)

            val sourceID = row.getCell(ExcelColumnNumberLinks.FIRST_APP_ID.value).stringCellValue.trim()
            val targetID = row.getCell(ExcelColumnNumberLinks.SECOND_APP_ID.value).stringCellValue.trim()

            if (sourceID.isEmpty() || targetID.isEmpty()) {
                continue
            }

            val appPattern = Pattern.compile(sourceApplicationPattern)
            val sourceMatcher = appPattern.matcher(sourceID)
            val targetMatcher = appPattern.matcher(targetID)

            if (sourceMatcher.find(0) || targetMatcher.find(0)) {
                result.getNode(sourceID)?.addDependedOnBy(targetID)
                result.getNode(targetID)?.addDepends(sourceID)
                log.info("> add link from $sourceID to $targetID")
            }
        }
    }

    private fun parseNodes(sheet: XSSFSheet, sourceApplicationPattern: String, result: Model) {
        log.info("PROCESS NODES")
        for (currentRow in 2..sheet.lastRowNum) {

            val row = sheet.getRow(currentRow)
            val sourceID = row.getCell(ExcelColumnNumberApplications.ID.value).stringCellValue.trim()
            val sourceName = row.getCell(ExcelColumnNumberApplications.NAME.value).stringCellValue.trim()
            val sourceDescription = row.getCell(ExcelColumnNumberApplications.DESCRIPTION.value).stringCellValue.trim()
            val sourceCluster = row.getCell(ExcelColumnNumberApplications.CLUSTER.value).stringCellValue.trim()
            val sourceStereotyp = row.getCell(ExcelColumnNumberApplications.STATUS.value).stringCellValue.trim()
            val location = row.getCell(ExcelColumnNumberApplications.LOCATION.value).stringCellValue.trim()


            if (!sourceID.isEmpty()) {
                val appIdPattern = Pattern.compile(sourceApplicationPattern)
                val sourceMatcher = appIdPattern.matcher(sourceID)
                if ((sourceMatcher.find(0)) && !result.containsNode(sourceID)) {
                    result.setNode(sourceID,
                            Node(sourceID,
                                    sourceName,
                                    sourceCluster,
                                    sourceDescription,
                                    sourceStereotyp,
                                    location))
                    log.info("> add node $sourceID")
                }
            }
        }
    }

    private fun openSheet(excelFileName: String, sheetName : String): XSSFSheet? {
        var result: XSSFSheet? = null
        var fis: FileInputStream? = null
        var wb: XSSFWorkbook? = null
        try {
            val file = File("./Input/$excelFileName")
            fis = FileInputStream(file)
            wb = XSSFWorkbook(fis)
            result =    wb.getSheet(sheetName )
            log.info("SHEET OPENED")
            log.info("> firstRowNum = " + result?.firstRowNum)
            log.info("> lastRowNum  = " + result?.lastRowNum)
        } catch (e: Exception) {
            log.info("*** OPEN SHEET FAILED : $e")
        } finally {
            wb?.close()
            fis?.close()
        }
        return result
    }


}