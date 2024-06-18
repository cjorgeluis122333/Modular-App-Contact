package cu.xetid.dtvc.androidtrainingapp.domain.data

import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local.ContactLocalDataSource
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.ContactRemoteDataSource
import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.common.coroutine.IoDispatcher
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactPictureProjection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactRepositoryImplement @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val contactLocalDataSource: ContactLocalDataSource,
    private val contactRemoteDataSource: ContactRemoteDataSource
) : ContactRepository {



    override suspend fun getUserName(): ResultValue<ContactNameProjection> {
        return withContext(ioDispatcher) {
            contactRemoteDataSource.getUserName()
        }
    }

    override suspend fun getUserLocation(): ResultValue<ContactLocationProjection> {
        return withContext(ioDispatcher) {
            contactRemoteDataSource.getUserLocation()
        }
    }

    override suspend fun getUserPicture(): ResultValue<ContactPictureProjection> {
        return withContext(ioDispatcher) {
            contactRemoteDataSource.getUserPicture()
        }
    }

    override fun selectAllContact(): Flow<List<Contact>> {
        return contactLocalDataSource.selectAllContact().flowOn(ioDispatcher)
    }

    override fun selectFavoriteContact(): Flow<List<Contact>> {
        return contactLocalDataSource.selectFavoriteContact().flowOn(ioDispatcher)
    }

    override fun selectContactSpecificById(contactId: Int): Flow<Contact> {
        return contactLocalDataSource.selectContactBySpecificId(contactId).flowOn(ioDispatcher)
    }

    override suspend fun insetNewContact(newContact: Contact) {
        withContext(ioDispatcher) {
            contactLocalDataSource.insetNewContact(newContact)
        }
    }

    override suspend fun updateContact(contactToUpdate: Contact) {
        withContext(ioDispatcher) {
            contactLocalDataSource.updateContact(contactToUpdate)
        }
    }

    override suspend fun deleteContact(contactDelete: Contact) {
        withContext(ioDispatcher) {
            contactLocalDataSource.deleteContact(contactDelete)
        }
    }


}