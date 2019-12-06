package io.kraftsman.sample

import io.kraftsman.sample.entities.Book
import io.kraftsman.sample.responds.BookRespond
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {

        }
    }

    Database.connect(
        url = "jdbc:mysql://127.0.0.1:8889/exposed_demo?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "root"
    )

    routing {
        get("/api/v1/books") {
            val books = transaction {
                Book.all().sortedByDescending { it.id }.map {
                    BookRespond(
                        title = it.title,
                        isbn = it.isbn,
                        publishedAt = it.publishedAt.toString("yyyy-MM-dd HH:mm:ss")
                    )
                }
            }

            call.respond(mapOf("books" to books))
        }
    }
}
