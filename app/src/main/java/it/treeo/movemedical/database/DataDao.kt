package it.treeo.movemedical.database

import androidx.room.*

@Dao
interface DataDao {
    @Query("SELECT * FROM Data")
    fun getAll(): List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: Data)

    @Query("DELETE FROM data where id=:id")
    fun deleteById(id: Int)

    @Update
     fun updateData(data: Data)

}