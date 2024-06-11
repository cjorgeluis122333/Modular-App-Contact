package cu.xetid.dtvc.androidtrainingapp.model.dto

data class Contact(
    var contactId: Int? = null,
    var firstName: String="",
    var lastName: String? = null,
    var city: String? = null,
    var thumbnail: String? = null,
    var fontNumber: String="",
    var favorite: Boolean = false
)