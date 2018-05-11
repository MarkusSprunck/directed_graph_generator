package control

object HtmlUtil {

    fun escape(s: String): String {
        val builder = StringBuilder()
        var previousWasASpace = false
        for (c in s.toCharArray()) {
            if (c == ' ') {
                if (previousWasASpace) {
                    builder.append("&nbsp;")
                    previousWasASpace = false
                    continue
                }
                previousWasASpace = true
            } else {
                previousWasASpace = false
            }
            when (c) {
                '<' -> builder.append("&lt;")
                '>' -> builder.append("&gt;")
                '&' -> builder.append("&amp;")
                '"' -> builder.append("&quot;")
                '\n' -> builder.append("<br>")
                '\t' -> builder.append("&nbsp; &nbsp; &nbsp;")
                else -> if (c.toInt() < 128) {
                    builder.append(c)
                } else {
                    builder.append("&#").append(c.toInt()).append(";")
                }
            }
        }
        return builder.toString()
    }
}