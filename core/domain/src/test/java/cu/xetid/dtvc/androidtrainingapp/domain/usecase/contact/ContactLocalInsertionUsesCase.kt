package com.example.contactnotmodular.core.domain.usecase.contact

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import javax.inject.Inject

class ContactLocalInsertionUsesCase @Inject constructor(private val contactRepository: ContactRepository) {

    suspend operator fun invoke(contactToInsert: Contact){
        contactRepository.insetNewContact(contactToInsert)
    }

}