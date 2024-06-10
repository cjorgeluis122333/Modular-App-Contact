package cu.xetid.dtvc.androidtrainingapp.network.mapper

import cu.xetid.dtvc.androidtrainingapp.model.network.PostResponseModel
import cu.xetid.dtvc.androidtrainingapp.model.oauth.AuthBearerTokenModel
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.PostResponseDTO
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.auth.AuthBearerTokenResponseDTO

internal fun AuthBearerTokenResponseDTO.asModel(): AuthBearerTokenModel = AuthBearerTokenModel(
    accessToken = accessToken, refreshToken = refreshToken
)

internal fun PostResponseDTO.asModel(): PostResponseModel = PostResponseModel(
    isSuccess = isSuccess, message = message
)
