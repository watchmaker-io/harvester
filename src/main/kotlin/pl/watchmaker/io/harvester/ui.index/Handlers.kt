package pl.watchmaker.io.harvester.index

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

class IndexPageHandler {

    fun indexPage(request: ServerRequest) = ServerResponse
            .ok()
            .render("index", mapOf("user" to "Dariusz Chojnacki"))

}