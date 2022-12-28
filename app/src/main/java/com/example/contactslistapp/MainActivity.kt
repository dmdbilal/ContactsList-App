package com.example.contactslistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.databinding.ActivityMainBinding
import com.example.contactslistapp.databinding.ItemContactBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contacts = mutableListOf(
            Contact("Bilal", "123456789"),
            Contact("Zubair", "6489749876"),
            Contact("Hakim", "8376249856"),
            Contact("Ajuma", "456789"),
            Contact("Farook", "345678987654"),
            Contact("Alima", "98734567")
        )

        val adapter = ContactsListAdapter(contacts)
        binding.rvContactsList.adapter = adapter
        binding.rvContactsList.layoutManager = LinearLayoutManager(this)
        binding.addButton.setOnClickListener {
            val name = binding.addName.text.toString()
            val phNo = binding.addPhNo.text.toString()
            val contact = Contact(name, phNo)
            contacts.add(contact)
            adapter.notifyItemInserted(contacts.size - 1)
        }
    }
}