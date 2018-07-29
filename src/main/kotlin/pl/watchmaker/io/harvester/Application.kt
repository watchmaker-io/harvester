package pl.watchmaker.io.harvester

import org.springframework.fu.application
import org.springframework.fu.module.data.mongodb.mongodb
import org.springframework.fu.module.logging.LogLevel
import org.springframework.fu.module.logging.level
import org.springframework.fu.module.logging.logback.consoleAppender
import org.springframework.fu.module.logging.logback.debug
import org.springframework.fu.module.logging.logback.logback
import org.springframework.fu.module.logging.logback.rollingFileAppender
import org.springframework.fu.module.logging.logging
import org.springframework.fu.module.mustache.mustache
import org.springframework.fu.module.webflux.jackson.jackson
import org.springframework.fu.module.webflux.netty.netty
import org.springframework.fu.module.webflux.webflux
import org.springframework.fu.ref
import pl.watchmaker.io.harvester.index.IndexPageHandler
import pl.watchmaker.io.harvester.index.uiIndexRoutes
import pl.watchmaker.io.harvester.yields.YieldHandler
import pl.watchmaker.io.harvester.yields.YieldRepository
import pl.watchmaker.io.harvester.yields.apiYieldRoutes
import java.io.File

val application = application {
    // FIXME bean definition from BoundedContext
    bean<IndexPageHandler>()
    bean<YieldRepository>()
    bean<YieldHandler>()
    logging {
        level(LogLevel.INFO)

        logback {
            debug(true)
            consoleAppender()
            rollingFileAppender(File(System.getProperty("java.io.tmpdir"), "log.txt"))
        }
    }
    webflux {
        val port = if (profiles.contains("test")) 8181 else 8080
        server(netty(port)) {
            mustache()
            codecs {
                jackson()
            }
            include {
                uiIndexRoutes(ref())
                        .and(apiYieldRoutes(ref()))
            }
        }
    }
    mongodb("mongodb://localhost/harvester")
}

fun main(args: Array<String>) = application.run(await = true)