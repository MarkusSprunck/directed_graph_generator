package control

import java.io.File
import java.util.logging.Logger

object FileUtil {

    private val log = Logger.getLogger(FileUtil::class.java.name)

    public fun load(name : String): String {
        val result = StringBuilder()
        File(ClassLoader.getSystemResource(name).file).forEachLine {
            result.append(it).append("\n")
        }
        return result.toString()
    }

}