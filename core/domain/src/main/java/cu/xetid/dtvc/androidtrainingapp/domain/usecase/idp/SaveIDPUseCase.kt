package cu.xetid.dtvc.androidtrainingapp.domain.usecase.idp

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import javax.inject.Inject

class SaveIDPUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
       try {
           when (val response = authRepository.getIdentityProviders()) {
               is ResultValue.Success -> {
                   authRepository.saveIDPs(response.data)
               }

               is ResultValue.Error -> {}
           }
       } catch (e: Exception) {
           e.printStackTrace()
       }
    }

}