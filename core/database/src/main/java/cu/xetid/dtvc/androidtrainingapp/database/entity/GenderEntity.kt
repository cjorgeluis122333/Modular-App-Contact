package cu.xetid.dtvc.androidtrainingapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gender")
data class GenderEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "probability") val probability: Float
)