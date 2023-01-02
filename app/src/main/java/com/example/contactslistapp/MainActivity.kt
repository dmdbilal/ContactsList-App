package com.example.contactslistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.database.ContactsListDao
import com.example.contactslistapp.databinding.ActivityMainBinding
import com.example.contactslistapp.databinding.ItemContactBinding
import com.example.contactslistapp.model.ContactsListViewModel
import com.example.contactslistapp.model.ContactsListViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, ContactsListFragment())
            commit()
        }
    }
}