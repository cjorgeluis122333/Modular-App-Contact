package cu.xetid.dtvc.androidtrainingapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val contactId: Int = 0,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "fontNumber") val fontNumber: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false
)