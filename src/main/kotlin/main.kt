import io.ktor.application.*
import io.ktor.http.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*

fun main(args: Array<String>) {

    val block: HTML.() -> Unit = {
        head {
            title { +"HTML Application" }
        }
        body {
            h1 { +"Sample application with Nicolas builders" }


            ul {
                classes = setOf("dropdown-menu")
                role = "menu"

                li { a("#") { +"Action" } }
                li { a("#") { +"Another action" } }
                li { a("#") { +"Something else here" } }
                li { classes = setOf("divider") }
                li { classes = setOf("dropdown-header"); +"Nav header" }
                li { a("#") { +"Separated link" } }
                li { a("#") { +"One more separated link" } }
            }
        }
    }

    val server = embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondHtml(block = block)
            }

        }
    }
    server.start(wait = true)
}
