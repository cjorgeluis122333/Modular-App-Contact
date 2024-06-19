package cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactSelectAllUsesCase @Inject constructor(private val contactRepository: ContactRepository) {

    operator fun invoke(): Flow<List<Contact>> = contactRepository.selectAllContact()


}