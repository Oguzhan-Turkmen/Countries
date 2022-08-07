package com.oguzhanturkmen.countries.model.Countries

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(
    tableName = "countries"
)

data class Country(
    @PrimaryKey(autoGenerate = false)
    val code: String,
    val name: String,
    val wikiDataId: String
):Serializable