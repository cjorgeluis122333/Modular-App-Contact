package cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local


import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import kotlinx.coroutines.flow.Flow

interface ContactLocalDataSource {


    fun selectAllContact(): Flow<List<Contact>>

    fun selectContactBySpecificId(contactId: Int): Flow<Contact>

    fun selectFavoriteContact():Flow<List<Contact>>
    suspend fun insetNewContact(newContact: Contact)

    suspend fun updateContact(contactToUpdate: Contact)

    suspend fun deleteContact(contactDelete: Contact)

}