package cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection

import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import javax.inject.Inject


class ContactSearchNameUsesCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
   suspend operator fun invoke(): ResultValue<ContactNameProjection> {
       return when(val result = contactRepository.getUserName()){
           is ResultValue.Error -> ResultValue.Error(result.exception)
           is ResultValue.Success -> ResultValue.Success(result.data)
       }
   }

}