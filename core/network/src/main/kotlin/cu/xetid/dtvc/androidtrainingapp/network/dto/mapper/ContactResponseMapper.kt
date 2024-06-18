package cu.xetid.dtvc.androidtrainingapp.network.dto.mapper

import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactPictureProjection
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


fun ContactResponse.toModelAsProjectionName(): ContactNameProjection = ContactNameProjection(
    first = name?.first.orEmpty(),
    last = name?.last.orEmpty(),
    title = name?.title.orEmpty()
)

fun ContactResponse.toModelAsProjectionLocation(): ContactLocationProjection = ContactLocationProjection(
    city = location?.city.orEmpty(),
    state =location?.state.orEmpty()
)
fun ContactResponse.toModelAsProjectionPicture(): ContactPictureProjection = ContactPictureProjection(
    thumbnail = picture?.thumbnail.orEmpty(),
)