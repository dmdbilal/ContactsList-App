package com.example.contactslistapp.model

import androidx.lifecycle.*
import com.example.contactslistapp.data.Contact
import com.example.contactslistapp.database.ContactsListDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactsListViewModel(private val contactsListDao: ContactsListDao): ViewModel() {

    fun allContacts(): Flow<List<Contact>> = contactsListDao.getAll()
//    fun getContactByName(name: String): List<Contact> = contactsListDao.getByName(name)
//    fun getContactByPhNo(phNo: String): List<Contact> = contactsListDao.getByPhNo(phNo)

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            contactsListDao.insert(contact)
        }
    }

}

class ContactsListViewModelFactory(private val contactsListDao: ContactsListDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactsListViewModel(contactsListDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}