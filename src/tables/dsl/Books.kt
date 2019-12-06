package io.kraftsman.sample.tables.dsl

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Books : Table() {
    val id: Column<Int> = integer("id")
                            .autoIncrement()
                            .primaryKey()
    val title: Column<String> = varchar("title", 255)
    val isbn: Column<String> = varchar("isbn", 13)
    val publishedAt: Column<DateTime> = datetime("published_at")
}
