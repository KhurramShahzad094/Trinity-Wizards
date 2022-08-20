package com.khurram.test.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khurram.test.R
import com.khurram.test.data.model.ContactModel
import com.khurram.test.databinding.ContactDetailFragmentBinding
import com.khurram.test.domain.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailFragment : Fragment() {

    private lateinit var binding: ContactDetailFragmentBinding
    val args: ContactDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()

        observer()
    }

    private fun getUserDetail() {
        updateUI(data = args.contactModel)
    }

    private fun clickListener() {
        binding.toolbar.findViewById<Button>(R.id.btCancel).setOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbar.findViewById<Button>(R.id.btSave).setOnClickListener {
            context?.showToast("In Progress")
        }
    }

    private fun observer() {
        getUserDetail()
    }

    private fun initialize() {
        clickListener()
    }

    private fun updateUI(data: ContactModel?) {
        binding.apply {
            data?.let {
                etFirstName.setText("${data.firstName}")
                etLastName.setText("${data.lastName}")
                etEmail.setText("${data.email}")
                etPhone.setText("${data.phone}")
            }
        }
    }
}