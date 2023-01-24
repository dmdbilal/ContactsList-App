package com.example.contactslistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.databinding.FragmentContactsListBinding
import com.example.contactslistapp.model.ContactsListViewModel
import com.example.contactslistapp.model.ContactsListViewModelFactory
import kotlinx.coroutines.launch

class ContactsListFragment : Fragment() {

    private var _binding: FragmentContactsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactsListViewModel by activityViewModels {
        ContactsListViewModelFactory(
            (activity?.application as ContactsListApplication).database.getContactDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactsListAdapter {}

        lifecycle.coroutineScope.launch {
            viewModel.allContacts().collect {
                adapter.submitList(it)
            }
        }

        binding.rvContactsList.adapter = adapter
        binding.rvContactsList.layoutManager = LinearLayoutManager(requireContext())
        binding.addButton.setOnClickListener {
            val name = binding.addName.text.toString()
            val phNo = binding.addPhNo.text.toString()
            val contact = Contact( 0, name, phNo)
            viewModel.insertContact(contact)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
