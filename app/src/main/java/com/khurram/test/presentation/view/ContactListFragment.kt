package com.khurram.test.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khurram.test.data.adapter.ContactAdapter
import com.khurram.test.data.model.ContactModel
import com.khurram.test.databinding.ContactListFragmentBinding
import com.khurram.test.domain.util.Helper
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type

@AndroidEntryPoint
class ContactListFragment : Fragment() {

    private lateinit var binding: ContactListFragmentBinding
    private var contactList = listOf<ContactModel>()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()

        observer()
    }

    private fun observer() {
        getUsersList()
    }

    private fun getUsersList() {
        val jsonFileString: String = Helper.getJsonFromAssets(context, "data.json")
        Log.i("data", jsonFileString)
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<ContactModel?>?>() {}.getType()
        contactList = gson.fromJson<List<ContactModel>>(jsonFileString, listUserType)

        contactAdapter.submitList(contactList)
    }

    private fun adapter() {
        contactAdapter = ContactAdapter(onItemClicked = { contactModel ->
            val directions =
                ContactListFragmentDirections.actionContactListFragmentToContactDetailFragment(contactModel = contactModel)
            findNavController().navigate(directions)
        })

        binding.apply {
            rvContacts.apply {
                adapter = contactAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun initialize(){
        adapter()
    }
}