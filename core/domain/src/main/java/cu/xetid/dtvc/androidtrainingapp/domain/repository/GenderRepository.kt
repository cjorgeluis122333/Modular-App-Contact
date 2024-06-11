package cu.xetid.dtvc.androidtrainingapp.domain.repository


import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import kotlinx.coroutines.flow.Flow

interface GenderRepository {

    fun selectSpecificUserGender(userName:String): Flow<Gender?>
   suspend fun fetchGenderUser(userName: String): ResultValue<Gender?>


}