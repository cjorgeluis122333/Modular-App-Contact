package cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth

import javax.inject.Inject

data class SingUseCase @Inject constructor(
    val loginUseCase: LoginUseCase,
    val recoveryPasswordUseCase: RecoveryPasswordUseCase,
)
