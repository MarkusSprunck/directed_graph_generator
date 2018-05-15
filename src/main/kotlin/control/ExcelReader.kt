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
        val sh = openSheet(excelFileName)
        val result = Model()

        if (sh == null) {
            log.info("*** SKIP PROCESSING OF NODES & LINKS")
            return result
        }

        log.info("PROCESS NODES")
        for (row in 3..sh.lastRowNum) {

            val currentRow = sh.getRow(row)

            val source = currentRow.getCell(ExcelColumnID.FIRST_APP_ID.value).stringCellValue
            val sourceLong = currentRow.getCell(ExcelColumnID.FIRST_APP_NAME.value).stringCellValue
            val sourceDescription = currentRow.getCell(ExcelColumnID.FIRST_APP_DESCRIPTION.value).stringCellValue

            val sourceMC = currentRow.getCell(ExcelColumnID.FIRST_APP_MC.value).stringCellValue

            val target = currentRow.getCell(ExcelColumnID.SECOND_APP_ID.value).stringCellValue
            val targetLong = currentRow.getCell(ExcelColumnID.SECOND_APP_NAME.value).stringCellValue
            val targetDescription = currentRow.getCell(ExcelColumnID.SECOND_APP_DESCRIPTION.value).stringCellValue
            val targetMC = currentRow.getCell(ExcelColumnID.SECOND_APP_MC.value).stringCellValue

            val appIdPattern = Pattern.compile(sourceApplicationPattern)
            val sourceMatcher = appIdPattern.matcher(source)
            val targetMatcher = appIdPattern.matcher(target)

            if (source.isEmpty() || target.isEmpty()) {
                continue
            }

            if ((sourceMatcher.find(0) || targetMatcher.find(0)) && !result.isNodeExisting(source)) {
                result.addNode(source, Node(source, sourceLong, sourceMC, sourceDescription))
                log.info("> add node $source")
            }

            if ((sourceMatcher.find(0) || targetMatcher.find(0)) && !result.isNodeExisting(target)) {
                result.addNode(target, Node(target, targetLong, targetMC, targetDescription))
                log.info("> add node $target")
            }
        }

        log.info("PROCESS LINKS")
        for (row in 3..sh.lastRowNum) {
            val currentRow = sh.getRow(row)

            val source = currentRow.getCell(ExcelColumnID.FIRST_APP_ID.value).stringCellValue
            val target = currentRow.getCell(ExcelColumnID.SECOND_APP_ID.value).stringCellValue

            if (source.isEmpty() || target.isEmpty()) {
                continue
            }

            val appPattern = Pattern.compile(sourceApplicationPattern)
            val sourceMatcher = appPattern.matcher(source)
            val targetMatcher = appPattern.matcher(target)

            if (sourceMatcher.find(0) || targetMatcher.find(0)) {
                result.getNode(source)?.addDependedOnBy(target)
                result.getNode(target)?.addDepends(source)
                log.info("> add link from $source to $target")
            }
        }

        return result
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
            log.info("*** SHEET FAILED $e")
        } finally {
            wb?.close()
            fis?.close()
        }
        return result
    }


}