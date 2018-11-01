package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import com.sw.engineering.candies.entity.Node
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.util.regex.Pattern


object ExcelReader {

    private val log = LoggerFactory.getLogger(ExcelReader::class.java)

    fun parseExcelSheet(excelFileName: String, filterPattern: String, strict: Boolean, colorMode: String): Model {
        log.info("FILE_NAME      = '$excelFileName'")
        log.info("FILTER_PATTERN = '$filterPattern'")
        val result = Model()


        val sh = openSheet(excelFileName, "Applications")
        if (sh == null) {
            log.info("*** SKIP PROCESSING APPLICATIONS - UNABLE TO OPEN SHEET $excelFileName")
            return result
        }
        parseNodes(sh, filterPattern, result, colorMode)


        val sh2 = openSheet(excelFileName, "Links")
        if (sh2 == null) {
            log.info("*** SKIP PROCESSING LINKS - UNABLE TO OPEN SHEET $excelFileName")
            return result
        }
        parseLinks(sh2, filterPattern, result, strict)

        return result
    }

    private fun openSheet(excelFileName: String, sheetName: String): XSSFSheet? {
        var result: XSSFSheet? = null
        var fis: FileInputStream? = null
        var wb: XSSFWorkbook? = null
        try {
            val file = File("./Input/$excelFileName")
            fis = FileInputStream(file)
            wb = XSSFWorkbook(fis)
            result = wb.getSheet(sheetName)
        } catch (e: Exception) {
            log.error("*** OPEN SHEET FAILED : $e")
        } finally {
            wb?.close()
            fis?.close()
        }
        return result
    }

    private fun parseLinks(sheet: XSSFSheet, filter: String, result: Model, strict: Boolean) {
        for (currentRow in 2..sheet.lastRowNum) {
            val row = sheet.getRow(currentRow)

            val sourceID = row.getCell(ExcelColumnNumberLinks.FIRST_APP_ID.value).stringCellValue.trim()
            val targetID = row.getCell(ExcelColumnNumberLinks.SECOND_APP_ID.value).stringCellValue.trim()
            val protocol = row.getCell(ExcelColumnNumberLinks.PROTOCOL.value).stringCellValue.trim()
            val interfaceName = row.getCell(ExcelColumnNumberLinks.INTERFACE_NAME.value).stringCellValue.trim()

            if (!sourceID.isEmpty() && !targetID.isEmpty()) {

                val appPattern = Pattern.compile(filter)
                val sourceMatcher = appPattern.matcher(sourceID)
                val targetMatcher = appPattern.matcher(targetID)

                if (strict && (sourceMatcher.find(0) && targetMatcher.find(0)) ||
                        !strict && (sourceMatcher.find(0) || targetMatcher.find(0))) {
                    result.getNode(sourceID)?.addDependedOnBy(targetID, "$interfaceName\\n($protocol)")
                    result.getNode(targetID)?.addDepends(sourceID, "$interfaceName\\n($protocol)")
                    log.info("> add link from $sourceID to $targetID description: '$interfaceName' ($protocol)")
                }
            }
        }
    }

    private fun parseNodes(sheet: XSSFSheet, sourceApplicationPattern: String, result: Model, colorMode: String) {
        for (currentRow in 1..sheet.lastRowNum) {

            val row = sheet.getRow(currentRow)
            val id = row.getCell(ExcelColumnNumberApplications.ID.value).stringCellValue.trim()
            val name = row.getCell(ExcelColumnNumberApplications.NAME.value).stringCellValue.trim()
            val description = row.getCell(ExcelColumnNumberApplications.DESCRIPTION.value).stringCellValue.trim()
            val cluster = row.getCell(ExcelColumnNumberApplications.SUB_CLUSTER.value).stringCellValue.trim()
            val status = row.getCell(ExcelColumnNumberApplications.STATUS.value).stringCellValue.trim()
            val location = row.getCell(ExcelColumnNumberApplications.LOCATION.value).stringCellValue.trim()


            if (!id.isEmpty()) {
                val appIdPattern = Pattern.compile(sourceApplicationPattern)
                val sourceMatcher = appIdPattern.matcher(id)
                if (sourceMatcher.find(0)) {
                    log.info("> add node with id=$id cluster='$cluster' status='$status' name='$name' colorMode='$colorMode'")
                    when (colorMode) {
                        "cluster" -> result.setNode(id, Node(id, name, description, cluster, location, status))
                        "status" -> result.setNode(id, Node(id, name, description, status, cluster, location))
                        "location" -> result.setNode(id, Node(id, name, description, location, status, cluster))
                        else -> result.setNode(id, Node(id, name, description, cluster, location, status))
                    }
                }
            }
        }
    }


}