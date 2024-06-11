package cu.xetid.dtvc.androidtrainingapp.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import cu.xetid.dtvc.androidtrainingapp.database.entity.GenderEntity

@Database(version = 1, entities = [GenderEntity::class])
abstract class GenderDataBase: RoomDatabase() {

abstract fun getGenderDao(): GenderDao

}