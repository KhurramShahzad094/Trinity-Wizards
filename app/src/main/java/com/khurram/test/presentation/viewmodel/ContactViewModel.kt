package com.khurram.test.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khurram.test.data.model.ContactModel
import com.khurram.test.domain.repository.DatabaseRepository
import com.khurram.test.domain.util.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {

    fun getContactListFromAssets(context: Context): List<ContactModel>? {
        val jsonFileString: String = Friend.getJsonFromAssets(context, "data.json")
        Log.i("data", jsonFileString)
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<ContactModel?>?>() {}.getType()
        return gson.fromJson(jsonFileString, listUserType)
    }

    fun insertAllContacts(contacts: List<ContactModel>){
        viewModelScope.launch {
            repository.insertContactList(contacts)
        }
    }

    fun getContact(id : String): LiveData<ContactModel?> {
        return repository.getContact(id = id).asLiveData()
    }

    fun updateContact(id : String, firstName : String, lastName : String, email : String, phone : String){
        viewModelScope.launch {
            repository.updateContact(id, firstName, lastName, email, phone)
        }
    }

    fun getAllContacts() : LiveData<List<ContactModel>>{
        return repository.getAllContacts().asLiveData()
    }

}