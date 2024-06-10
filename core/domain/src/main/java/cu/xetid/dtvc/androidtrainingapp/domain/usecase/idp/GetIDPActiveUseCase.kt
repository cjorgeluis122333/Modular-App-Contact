package cu.xetid.dtvc.androidtrainingapp.domain.usecase.idp

import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIDPActiveUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Flow<IdentityProvider> = authRepository.getActiveIDP()

}