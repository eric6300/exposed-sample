package io.kraftsman.sample.crud

import com.github.javafaker.Faker
import io.kraftsman.sample.tables.dsl.Books
import org.jetbrains.exposed.sql.*
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
        Books.insert {
            it[title] = faker.book().title().toString()
            it[isbn] = faker.number().digits(13).toString()
            it[publishedAt] = DateTime.now()
        }
    }

    transaction {
        Books.selectAll().map {
            println(it[Books.title])
        }

        Books.select {
            Books.id eq 2 and Books.publishedAt.between(DateTime.now().minusHours(3), DateTime.now())
        }
        .orderBy(Books.id to SortOrder.ASC)
        .map {
            println(it[Books.title])
        }
    }

    transaction {
        Books.update({ (Books.id lessEq 4 ) and (Books.id greaterEq  3) }) {
            it[title] = "Updated Title"
        }
    }

    transaction {
        Books.deleteWhere {
            Books.id eq 2
        }
    }
}
