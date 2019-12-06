package io.kraftsman.sample.tables.dsl

import org.jetbrains.exposed.sql.Table

object Books : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = varchar("title", 255)
    val isbn = varchar("isbn", 13)
    val publishedAt = datetime("published_at")
}
