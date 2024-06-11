package cu.xetid.dtvc.androidtrainingapp.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import cu.xetid.dtvc.androidtrainingapp.database.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 2)
abstract class ContactDataBase:RoomDatabase() {
abstract fun getContactDao(): ContactDao
}