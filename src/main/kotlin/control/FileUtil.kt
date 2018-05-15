package control

import java.io.File

object FileUtil {

    private const val NL = '\n'

    fun load(name: String): String {
        val result = StringBuilder()
        File(ClassLoader.getSystemResource(name).file).forEachLine {
            result.append(it).append(NL)
        }
        return result.toString()
    }

}