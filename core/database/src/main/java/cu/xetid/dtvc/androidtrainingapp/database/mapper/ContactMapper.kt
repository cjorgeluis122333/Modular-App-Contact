package cu.xetid.dtvc.androidtrainingapp.database.mapper

import cu.xetid.dtvc.androidtrainingapp.database.entity.ContactEntity
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact

internal fun ContactEntity.toModel(): Contact = Contact(
    contactId = contactId,
    firstName = firstName,
    lastName = lastName.orEmpty(),
    city = city.orEmpty(),
    thumbnail = thumbnail.orEmpty(),
    fontNumber = fontNumber,
    favorite = favorite
)
internal fun Contact.toEntity(): ContactEntity = ContactEntity(
    contactId = contactId?:0,
    firstName = firstName,
    lastName = lastName.orEmpty(),
    city = city.orEmpty(),
    thumbnail = thumbnail.orEmpty(),
    fontNumber = fontNumber,
    favorite = favorite
)

