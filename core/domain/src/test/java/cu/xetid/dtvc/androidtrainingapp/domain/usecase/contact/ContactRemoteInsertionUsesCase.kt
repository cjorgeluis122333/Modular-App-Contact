package com.example.contactnotmodular.core.domain.usecase.contact

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import javax.inject.Inject

class ContactRemoteInsertionUsesCase @Inject constructor(private val contactRepository: ContactRepository) {
/*
    suspend operator fun invoke(): ResultValue<Contact> {
        val contact: Contact? = null
        val isNameSusses =
            when (val result = contactRepository.getUserName()) {
                is ResultValue.Error -> ResultValue.Error(result.exception)
                is ResultValue.Success -> {
                   contact.let {
                       it?.firstName = result.data.first
                       it?.lastName = result.data.first
                   }
                    ResultValue.Success(true)
                }
            }

        val getLocation = when (val result = contactRepository.getUserLocation()) {
            is ResultValue.Error -> ResultValue.Error(result.exception)
            is ResultValue.Success -> {
                contact.let {
                    it?.city = result.data.city
                }
                ResultValue.Success(true)
            }
        }

        val getPicture = when (val result = contactRepository.getUserPicture()) {
            is ResultValue.Error -> ResultValue.Error(result.exception)
            is ResultValue.Success -> {
                contact.let {
                    it?.thumbnail = result.data.thumbnail
                }
                ResultValue.Success(result.data)}
        }


    }
*/
}