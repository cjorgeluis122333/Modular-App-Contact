package cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        email: String, password: String
    ): ResultValue<Boolean> {
      return when (val loginResult = authRepository.singIn(email = email, password = password)) {
           is ResultValue.Success -> {
               authRepository.saveAccessToken(loginResult.data.accessToken)
               authRepository.saveRefreshToken(loginResult.data.refreshToken)
               authRepository.saveActiveIDP(IdentityProvider.Ticket)
               ResultValue.Success(true)
           }

           is ResultValue.Error -> {
               ResultValue.Error(loginResult.exception)
           }
       }
    }

}