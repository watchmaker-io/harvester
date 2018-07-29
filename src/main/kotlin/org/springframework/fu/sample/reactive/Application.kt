
package org.springframework.fu.sample.reactive

import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.fu.application
import org.springframework.fu.module.data.mongodb.mongodb
import org.springframework.fu.module.logging.LogLevel.*
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
import java.io.File

val app = application {
	bean<UserRepository>()
	bean<UserHandler>()
	logging {
		level(INFO)
		level("org.springframework", DEBUG)
		level<DefaultListableBeanFactory>(WARN)

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
			include { routes(ref()) }
		}
	}

	configuration(configuration)
	mongodb("mongodb://localhost/harvester")
}

//fun main(args: Array<String>) = app.run(await = true)
