package io.kraftsman.sample.relations

import io.kraftsman.sample.entities.Author
import io.kraftsman.sample.entities.Book
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect(
        url = "jdbc:mysql://127.0.0.1:8889/exposed_demo?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "root"
    )

    transaction {
        Book.all().map {
            println("(${it.id}) ${it.title} by ${it.author.name}")
        }

        Author.all().map { author ->
            author.books.forEach {
                println("${author.name} wrote a book called ${it.title}")
            }
        }
    }
}
