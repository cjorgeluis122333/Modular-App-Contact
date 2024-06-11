package cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import javax.inject.Inject

class ContactDeleteUsesCase @Inject constructor(private val contactRepository: ContactRepository) {
suspend operator fun invoke (contactToDelete: Contact){
    contactRepository.deleteContact(contactToDelete)
}

}