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
// R.layout.fragment_contacts_list
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

//        val contacts = mutableListOf(
//            Contact(0,"Alima", "98734567"),
//            Contact(1, "Bilal", "123456789"),
//            Contact(2, "Zubair", "6489749876"),
//            Contact(3, "Hakim", "8376249856"),
//            Contact(4, "Ajuma", "456789"),
//            Contact(5, "Farook", "345678987654")
//        )

//        val contacts = viewModel.allContacts()

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
//            contacts.add(contact)
            viewModel.insertContact(contact)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}