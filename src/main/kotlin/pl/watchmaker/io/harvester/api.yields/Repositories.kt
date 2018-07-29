package pl.watchmaker.io.harvester.yields

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import reactor.core.publisher.Mono

class YieldRepository(
    private val template: ReactiveMongoTemplate
) {
    fun findAll() = template.findAll<Yield>()

    fun save(y: Mono<Yield>) = template.save(y)

}