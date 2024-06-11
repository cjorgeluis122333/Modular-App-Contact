package cu.xetid.dtvc.androidtrainingapp.database.datasource

import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local.ContactLocalDataSource
import cu.xetid.dtvc.androidtrainingapp.database.dao.ContactDao
import cu.xetid.dtvc.androidtrainingapp.database.mapper.toEntity
import cu.xetid.dtvc.androidtrainingapp.database.mapper.toModel
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactLocalDataSourceImplement @Inject constructor(
    private val contactDao: ContactDao
) : ContactLocalDataSource {
    override fun selectAllContact(): Flow<List<Contact>> {
        return contactDao.selectAllContact().map {
            it.map { contactEntity ->
                contactEntity.toModel()
            }
        }
    }

    override fun selectContactBySpecificId(contactId: Int): Flow<Contact> {
        return contactDao.selectContactById(contactId = contactId).map { it.toModel() }
    }

    override suspend fun insetNewContact(newContact: Contact) {
        contactDao.insetNewContact(newContact.toEntity())
    }

    override suspend fun updateContact(contactToUpdate: Contact) {
        contactDao.updateContact(contactToUpdate.toEntity())
    }

    override suspend fun deleteContact(contactDelete: Contact) {
        contactDao.deleteContact(contactDelete.toEntity())
    }

}