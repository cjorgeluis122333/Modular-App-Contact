package cu.xetid.dtvc.androidtrainingapp.network.datasource

import cu.xetid.dtvc.androidtrainingapp.domain.datasource.remote.RemoteDataSource
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.exception.createExceptionByErrorCode
import retrofit2.HttpException

abstract class RemoteDatasourceAbstraction : RemoteDataSource {
    fun checkError(resultValue: ResultValue<Any>): ResultValue<Any> =
        if (resultValue is ResultValue.Error) {
            val ex: Exception = createExceptionByErrorCode(
                errorCode = (resultValue.exception as? HttpException)?.code()
            )
            resultValue.copy(exception = ex)
        } else {
            resultValue
        }
}