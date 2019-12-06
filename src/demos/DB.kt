package io.kraftsman.sample.demos

import com.github.javafaker.Faker
import io.kraftsman.sample.entities.Author
import io.kraftsman.sample.entities.Book
import io.kraftsman.sample.tables.dao.Authors
import io.kraftsman.sample.tables.dao.Books
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
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
        SchemaUtils.drop(Authors, Books)
        SchemaUtils.create(Authors, Books)
    }

    transaction {
        for (i in 1..10) {
            val newAuthor = Author.new {
                name = faker.book().author()
            }

            Book.new {
                title = faker.book().title()
                isbn = faker.number().digits(13)
                publishedAt = DateTime.now().minusDays(i)
                author = newAuthor
            }
        }
    }
}
