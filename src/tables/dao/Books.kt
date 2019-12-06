package io.kraftsman.sample.tables.dao

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.joda.time.DateTime

object Books : IntIdTable() {
    val title: Column<String> = varchar("title", 255)
    val isbn: Column<String> = varchar("isbn", 13)
    val publishedAt: Column<DateTime> = datetime("published_at")
    val author = reference("author", Authors)
}
