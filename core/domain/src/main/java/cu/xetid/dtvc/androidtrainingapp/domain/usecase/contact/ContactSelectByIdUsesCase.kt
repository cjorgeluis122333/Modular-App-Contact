package cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactSelectByIdUsesCase @Inject constructor(private val contactRepository: ContactRepository) {
    operator fun invoke(contactId:Int): Flow<Contact> {
       return contactRepository.selectContactSpecificById(contactId = contactId)
    }

}