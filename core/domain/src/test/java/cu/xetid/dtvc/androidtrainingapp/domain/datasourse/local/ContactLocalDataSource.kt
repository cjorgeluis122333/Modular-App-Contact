package com.example.contactnotmodular.core.domain.datasourse.local

import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import kotlinx.coroutines.flow.Flow

interface ContactLocalDataSource {

    fun selectAllContact(): Flow<List<Contact>>

    suspend fun insetNewContact(newContact: Contact)

    suspend fun updateContact(contactToUpdate: Contact)

    suspend fun deleteContact(contactDelete: Contact)


}