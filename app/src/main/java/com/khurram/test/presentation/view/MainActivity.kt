package com.khurram.test.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.khurram.test.databinding.ActivityMainBinding
import com.khurram.test.presentation.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactViewModel : ContactViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactList = contactViewModel.getContactListFromAssets(this)
        contactViewModel.insertAllContacts(contactList!!)
}

}