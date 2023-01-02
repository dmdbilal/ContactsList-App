package com.example.contactslistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.databinding.ItemContactBinding

// With latest binding view
class ContactsListAdapter(
    private val onItemClicked: (Contact) -> Unit
): ListAdapter<Contact, ContactsListAdapter.ContactsListViewHolder>(DiffCallBack) {

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.name == newItem.name && oldItem.phNo == newItem.phNo
            }
        }
    }

    inner class ContactsListViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ContactsListViewHolder(binding)

        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        val contact = getItem(position)
        holder.binding.apply {
            name.text = contact.name
            phNo.text = contact.phNo
        }
    }
}