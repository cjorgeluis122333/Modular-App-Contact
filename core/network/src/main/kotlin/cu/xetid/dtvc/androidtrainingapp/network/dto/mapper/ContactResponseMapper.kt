package cu.xetid.dtvc.androidtrainingapp.network.dto.mapper

import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactPictureProjection
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactLocation
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactName
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactPicture
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactResponse


fun ContactResponse.toModel(): Contact = Contact(
    firstName = this.name?.first.orEmpty(),
    city = this.location?.city,
    favorite = false,
    lastName = this.name?.last,
    thumbnail = this.picture?.thumbnail,
    fontNumber = "",
    contactId = null
)


fun ContactName.toModelAsProjectionName(): ContactNameProjection = ContactNameProjection(
    first = first,
    last = last,
    title = title
)

fun ContactLocation.toModelAsProjectionLocation(): ContactLocationProjection = ContactLocationProjection(
    city = city,
    state = state
)
fun ContactPicture.toModelAsProjectionPicture(): ContactPictureProjection = ContactPictureProjection(
    thumbnail = thumbnail,
)