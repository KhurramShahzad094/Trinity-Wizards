package com.khurram.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khurram.test.data.db.dao.ContactDao
import com.khurram.test.data.model.ContactModel

@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}