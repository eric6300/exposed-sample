package io.kraftsman.sample.entities

import io.kraftsman.sample.tables.dao.Authors
import io.kraftsman.sample.tables.dao.Books
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Author(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Author>(Authors)

    var name by Authors.name
    val books by Book referrersOn Books.author
}
