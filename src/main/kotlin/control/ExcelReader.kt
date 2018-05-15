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

        val sh = openSheet(excelFileName)
        if (sh == null) {
            log.info("*** SKIP PROCESSING - UNABLE TO OPEN SHEET $excelFileName")
            return result
        }

        parseNodes(sh, sourceApplicationPattern, result)
        parseLinks(sh, sourceApplicationPattern, result)
        return result
    }

    private fun parseLinks(sheet: XSSFSheet, sourceApplicationPattern: String, result: Model) {
        log.info("PROCESS LINKS")
        for (currentRow in 3..sheet.lastRowNum) {
            val row = sheet.getRow(currentRow)

            val sourceID = row.getCell(ExcelColumnNumber.FIRST_APP_ID.value).stringCellValue
            val targetID = row.getCell(ExcelColumnNumber.SECOND_APP_ID.value).stringCellValue

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
        for (currentRow in 3..sheet.lastRowNum) {

            val row = sheet.getRow(currentRow)

            val sourceID = row.getCell(ExcelColumnNumber.FIRST_APP_ID.value).stringCellValue
            val sourceName = row.getCell(ExcelColumnNumber.FIRST_APP_NAME.value).stringCellValue
            val sourceDescription = row.getCell(ExcelColumnNumber.FIRST_APP_DESCRIPTION.value).stringCellValue
            val sourceCluster = row.getCell(ExcelColumnNumber.FIRST_APP_CLUSTER.value).stringCellValue

            val targetID = row.getCell(ExcelColumnNumber.SECOND_APP_ID.value).stringCellValue
            val targetName = row.getCell(ExcelColumnNumber.SECOND_APP_NAME.value).stringCellValue
            val targetDescription = row.getCell(ExcelColumnNumber.SECOND_APP_DESCRIPTION.value).stringCellValue
            val targetCluster = row.getCell(ExcelColumnNumber.SECOND_APP_CLUSTER.value).stringCellValue

            // Don't process emnty rows
            if (sourceID.isEmpty() || targetID.isEmpty()) {
                continue
            }

            val appIdPattern = Pattern.compile(sourceApplicationPattern)
            val sourceMatcher = appIdPattern.matcher(sourceID)
            val targetMatcher = appIdPattern.matcher(targetID)

            if ((sourceMatcher.find(0) || targetMatcher.find(0)) && !result.containsNode(sourceID)) {
                result.setNode(sourceID, Node(sourceID, sourceName, sourceCluster, sourceDescription))
                log.info("> add node $sourceID")
            }

            if ((sourceMatcher.find(0) || targetMatcher.find(0)) && !result.containsNode(targetID)) {
                result.setNode(targetID, Node(targetID, targetName, targetCluster, targetDescription))
                log.info("> add node $targetID")
            }
        }
    }

    private fun openSheet(excelFileName: String): XSSFSheet? {
        var result: XSSFSheet? = null
        var fis: FileInputStream? = null
        var wb: XSSFWorkbook? = null
        try {
            val file = File("./Input/$excelFileName")
            fis = FileInputStream(file)
            wb = XSSFWorkbook(fis)
            result = wb.getSheet("Sheet1")
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