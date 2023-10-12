package xyz.senzd.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.issRouting() {
    route("/iss") {
        get {
            call.respondText("No iss found", status = HttpStatusCode.OK)
        }
        get("{id?}") {
            val id = call.parameters["id"]
            call.respondText("No satellite found with id $id", status = HttpStatusCode.OK)
        }
    }
}