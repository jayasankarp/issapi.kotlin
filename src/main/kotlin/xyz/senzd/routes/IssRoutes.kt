package xyz.senzd.routes

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.Request

@Serializable
data class SatelliteData(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("latitude") val latitude: Float? = null,
    @SerializedName("longitude") val longitude: Float? = null,
    @SerializedName("altitude") val altitude: Float? = null,
    @SerializedName("velocity") val velocity: Float? = null,
    @SerializedName("timestamp") val timestamp: Int? = null,
)

fun Route.issRouting() {
    route("/iss") {
        get {
            val data: SatelliteData = getIssData()
            if(data.id > 0) call.respond(data)
            call.respond(message = "Satellite data not found", status = HttpStatusCode.NotFound)
        }
        get("{id?}") {
            val id = call.parameters["id"]
            call.respondText("No satellite found with id $id", status = HttpStatusCode.OK)
        }
    }
}

fun getIssData(): SatelliteData {
    val apiUrl = "https://api.wheretheiss.at/v1/satellites/25544"

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(apiUrl)
        .build()

    var customObject: SatelliteData

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            customObject = SatelliteData();
        }

        val responseBody = response.body?.string()
        val gson = Gson()
        customObject = gson.fromJson(responseBody, SatelliteData::class.java)
    }

    return customObject
}