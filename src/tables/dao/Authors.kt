package io.kraftsman.sample.tables.dao

import org.jetbrains.exposed.dao.IntIdTable

object Authors: IntIdTable() {
    val name = varchar("name", 255)
}
