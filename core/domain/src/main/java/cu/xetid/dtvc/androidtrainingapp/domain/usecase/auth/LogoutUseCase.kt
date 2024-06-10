package cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): ResultValue<Boolean> =
        authRepository.revokeToken()

}
