package cu.xetid.dtvc.androidtrainingapp.domain.usecase.idp

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import javax.inject.Inject

class SaveIDPActiveUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(provider: IdentityProvider) = authRepository.saveActiveIDP(provider)

}
