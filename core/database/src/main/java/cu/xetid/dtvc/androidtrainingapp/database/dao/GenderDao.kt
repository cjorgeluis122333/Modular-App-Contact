package cu.xetid.dtvc.androidtrainingapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cu.xetid.dtvc.androidtrainingapp.database.entity.GenderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenderDao {

    @Query("Select * From gender")
    fun selectAllUserGender(): Flow<List<GenderEntity>>

    @Query("Select * From gender where name=:name limit 1")
    fun getSpecificUserByName(name: String): Flow<GenderEntity?>

    @Insert()
    suspend fun insertListOfUserEntry(userEntityToSave: List<GenderEntity>)

    @Insert()
    suspend fun insertUserEntry(userEntityToSave: GenderEntity)


}