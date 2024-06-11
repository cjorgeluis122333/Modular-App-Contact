package cu.xetid.dtvc.androidtrainingapp.network.dto.response

import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactLocation
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactName
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactPicture

data class ContactResponse(
    val name: ContactName?,
    val location: ContactLocation?,
    val picture: ContactPicture?
)