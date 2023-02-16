package it.treeo.movemedical.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.DateFormat

@Entity
class Data (
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "time_value")
    val time_value: String?,
    @ColumnInfo(name = "position")
    val position: Int?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "description")
    val description: String?
    ):Serializable