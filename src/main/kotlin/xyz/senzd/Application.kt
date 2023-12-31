package xyz.senzd

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import xyz.senzd.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
