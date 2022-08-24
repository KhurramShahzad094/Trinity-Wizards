package com.khurram.test.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khurram.test.data.model.ContactModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts : List<ContactModel>)

    @Query("SELECT * FROM contact")
    fun getAllContacts(): Flow<List<ContactModel>>

    @Query("DELETE FROM contact")
    suspend fun deleteAllContacts()

}