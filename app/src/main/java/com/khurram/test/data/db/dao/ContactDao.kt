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

    @Query("UPDATE contact SET firstName=:firstName, lastName=:lastName, email=:email,phone=:phone WHERE id = :id")
    suspend fun updateContactDetail(id : String, firstName : String, lastName : String, email : String, phone : String)

    @Query("SELECT * FROM contact WHERE id=:id")
    fun getContactDetail(id : String): Flow<ContactModel>

}