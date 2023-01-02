package com.example.contactslistapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactslistapp.data.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts ORDER BY Name")
    fun getAll(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE Name = :name")
    fun getByName(name: String): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE PhNo = :phNo")
    fun getByPhNo(phNo: String): Flow<List<Contact>>
}