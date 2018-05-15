import control.MainApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args) {
        System.setProperty("kotlin.version", KotlinVersion.CURRENT.toString())
    }
}
