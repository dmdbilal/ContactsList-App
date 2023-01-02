package com.example.contactslistapp

import android.app.Application
import com.example.contactslistapp.database.ContactRoomDatabase

class ContactsListApplication : Application() {
    val database: ContactRoomDatabase by lazy { ContactRoomDatabase.getDatabase(this) }
}