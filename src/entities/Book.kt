package io.kraftsman.sample.entities

import io.kraftsman.sample.tables.dao.Books
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Book(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Book>(Books)

    var title by Books.title
    var isbn by Books.isbn
    var publishedAt by Books.publishedAt
    var author by Author referencedOn Books.author
}
