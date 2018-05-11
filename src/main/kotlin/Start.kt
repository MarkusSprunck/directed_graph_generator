import control.MainApplication
import org.springframework.boot.runApplication
import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args) {
        System.setProperty("kotlin.version", KotlinVersion.CURRENT.toString())
    }
}
