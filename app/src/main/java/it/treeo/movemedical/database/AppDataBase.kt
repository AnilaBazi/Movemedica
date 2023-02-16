package it.treeo.movemedical.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dataDao(): DataDao

}