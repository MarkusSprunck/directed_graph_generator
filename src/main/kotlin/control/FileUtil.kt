package control

import java.io.File

object FileUtil {

    fun load(name: String): String {
        val result = StringBuilder()
        File(ClassLoader.getSystemResource(name).file).forEachLine {
            result.append(it).append("\n")
        }
        return result.toString()
    }

}