package cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import javax.inject.Inject

class ContactSearchLocationUsesCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
   suspend operator fun invoke(): ResultValue<ContactLocationProjection> {
       return when(val result = contactRepository.getUserLocation()){
           is ResultValue.Error -> ResultValue.Error(result.exception)
           is ResultValue.Success -> ResultValue.Success(result.data)
       }
   }

}