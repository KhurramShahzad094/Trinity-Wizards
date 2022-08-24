package com.khurram.test.domain.repository

import com.khurram.test.data.db.ContactDatabase
import com.khurram.test.data.model.ContactModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private var database: ContactDatabase
) {

    private val contactDao = database.contactDao()

    suspend fun insertContactList(contacts: List<ContactModel>) = contactDao.insertContacts(contacts = contacts)

    fun getContact(id: String) = contactDao.getContactDetail(id = id)

    fun getAllContacts() = contactDao.getAllContacts()

    suspend fun updateContact(id : String, firstName : String, lastName : String, email : String, phone : String) = contactDao.updateContactDetail(id, firstName, lastName, email, phone)
 }