package cu.xetid.dtvc.androidtrainingapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cu.xetid.dtvc.androidtrainingapp.database.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {


    @Query("Select * from contact ORDER By firstName Asc")
    fun selectAllContact(): Flow<List<ContactEntity>>

    @Query("Select * from contact where :contactId = contactId")
    fun selectContactById(contactId:Int): Flow<ContactEntity>

    @Query("Select * from contact where favorite==1 Order by firstName Asc")
    fun selectFavoriteContact():Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetNewContact(newContact: ContactEntity)

    @Update
    suspend fun updateContact(contactToUpdate: ContactEntity)

    @Delete()
    suspend fun deleteContact(contactDelete: ContactEntity)

}