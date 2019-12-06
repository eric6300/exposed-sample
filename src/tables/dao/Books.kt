package io.kraftsman.sample.tables.dao

import org.jetbrains.exposed.dao.IntIdTable

object Books : IntIdTable() {
    val title = varchar("title", 255)
    val isbn = varchar("isbn", 13)
    val publishedAt = datetime("published_at")
    val author = reference("author", Authors)
}
