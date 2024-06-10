package cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): ResultValue<Boolean> {
        val refreshToken = authRepository.getRefreshToken()

        return if (!refreshToken.isNullOrEmpty()) {
            val result = authRepository.refreshToken(refreshToken)
            if (result is ResultValue.Success) {
                authRepository.saveAccessToken(result.data.accessToken)
                authRepository.saveRefreshToken(result.data.refreshToken)
                ResultValue.Success(true)
            }else{
                result as ResultValue.Error
            }
        } else
            ResultValue.Success(false)
    }

}
