package io.kraftsman.sample.demos

import com.github.javafaker.Faker
import io.kraftsman.sample.entities.Book
import io.kraftsman.sample.tables.dsl.Books
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

fun main() {
    Database.connect(
        url = "jdbc:mysql://127.0.0.1:8889/exposed_demo?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "root"
    )

    val faker = Faker()

    transaction {
        Book.new {
            title = faker.book().title()
            isbn = faker.number().digits(13).toString()
            publishedAt = DateTime.now()
        }
    }

    transaction {
        Book.all()
        Book.find {
            Books.id eq 3
        }
        .sortedBy { it.id }
        .map {
            println(it.title)
        }
    }

    transaction {
        val book = Book.findById(1)
        if (book != null) {
            book.title = "Updated XXX"
        }
    }

    transaction {
        val book = Book.findById(1)
        book?.delete()
    }
}
