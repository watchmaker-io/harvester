package pl.watchmaker.io.harvester.index

import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.router

fun uiIndexRoutes(indexPageHandler: IndexPageHandler) = router {
    accept(TEXT_HTML).nest {
        GET("/", indexPageHandler::indexPage)
    }
}