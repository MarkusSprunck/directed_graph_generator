package control

object FileUtil {

    private const val NL = '\n'

    fun load(name: String): String {
        return javaClass.classLoader.getResource(name).readText()
    }


}