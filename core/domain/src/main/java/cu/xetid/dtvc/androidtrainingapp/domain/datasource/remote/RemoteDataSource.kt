package cu.xetid.dtvc.androidtrainingapp.domain.datasource.remote

import cu.xetid.dtvc.androidtrainingapp.model.ResultValue

interface RemoteDataSource {

    suspend fun <T> safeApiCall(request: suspend () -> T): ResultValue<T> = try {
        val response = request.invoke()
        ResultValue.Success(response)
    } catch (e: Exception) {
        ResultValue.Error(e)
    }

}
