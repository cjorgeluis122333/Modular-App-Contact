package cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.network.PostResponseModel
import javax.inject.Inject

class RecoveryPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String): ResultValue<PostResponseModel> =
        authRepository.recoveryPassword(email = email)

}
