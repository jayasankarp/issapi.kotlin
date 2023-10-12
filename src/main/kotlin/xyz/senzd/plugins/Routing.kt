package xyz.senzd.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xyz.senzd.routes.issRouting

fun Application.configureRouting() {
    routing {
        issRouting();
    }
}
