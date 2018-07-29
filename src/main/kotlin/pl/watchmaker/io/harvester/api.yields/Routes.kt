package pl.watchmaker.io.harvester.yields

import org.springframework.web.reactive.function.server.router

fun apiYieldRoutes(yieldHandler: YieldHandler) = router {
    "/api/yield".nest {
        GET("/", yieldHandler::all)
        POST("/", yieldHandler::save)
    }
}
