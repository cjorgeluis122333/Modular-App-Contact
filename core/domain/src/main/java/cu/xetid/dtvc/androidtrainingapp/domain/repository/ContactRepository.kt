package cu.xetid.dtvc.androidtrainingapp.domain.repository


import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactPictureProjection
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun getUserName(): ResultValue<ContactNameProjection>

    suspend fun getUserLocation(): ResultValue<ContactLocationProjection>

    suspend fun getUserPicture(): ResultValue<ContactPictureProjection>

    fun selectAllContact(): Flow<List<Contact>>
    fun selectFavoriteContact(): Flow<List<Contact>>

    fun selectContactSpecificById(contactId: Int): Flow<Contact>

    suspend fun insetNewContact(newContact: Contact)

    suspend fun updateContact(contactToUpdate: Contact)

    suspend fun deleteContact(contactDelete: Contact)



}