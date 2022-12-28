package com.example.contactslistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.databinding.ItemContactBinding

// With latest binding view
class ContactsListAdapter(
    var contacts: List<Contact>
): RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder>() {

    inner class ContactsListViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        return ContactsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        holder.binding.apply {
            name.text = contacts[position].name
            phNo.text = contacts[position].phNo
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}