package pl.watchmaker.io.harvester.yields

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.created
import reactor.core.publisher.toMono
import java.net.URI.create

class YieldHandler(
    private val yieldRepository: YieldRepository
) {
    fun all(request: ServerRequest) = ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(yieldRepository.findAll())

    fun save(request: ServerRequest) = yieldRepository.save(request.bodyToMono())
            .flatMap {
                created(create("/api/yield/${it.id}"))
                        .json()
                        .body(it.toMono())

            }
}