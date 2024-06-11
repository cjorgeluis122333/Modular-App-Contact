package cu.xetid.dtvc.androidtrainingapp.database.datasource

import cu.xetid.dtvc.androidtrainingapp.database.dao.GenderDao
import cu.xetid.dtvc.androidtrainingapp.database.mapper.toGender
import cu.xetid.dtvc.androidtrainingapp.database.mapper.toGenderEntity
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local.GenderLocalDataSorcerer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GenderLocalDataSorcererImplement @Inject constructor(
    private val genderDao: GenderDao
) :
    GenderLocalDataSorcerer {
    override fun getSpecificUserByName(name: String): Flow<Gender?> =
        genderDao.getSpecificUserByName(name).map { it?.toGender() }

    override suspend fun insertListOfUserEntry(userEntityToSave: List<Gender>) {
        return  genderDao.insertListOfUserEntry(userEntityToSave.map { it.toGenderEntity() })
    }

    override suspend fun insertUserEntry(userEntityToSave: Gender) {
        return  genderDao.insertUserEntry(userEntityToSave.toGenderEntity())
    }

}