package cu.xetid.dtvc.androidtrainingapp.database.di

import android.content.Context
import androidx.room.Room
import cu.xetid.dtvc.androidtrainingapp.database.dao.ContactDataBase
import cu.xetid.dtvc.androidtrainingapp.database.dao.GenderDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    //                                          Room Gender
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): GenderDataBase =
        Room.databaseBuilder(context = context, name = "genderDBB", klass = GenderDataBase::class.java)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(userDDB: GenderDataBase)=userDDB.getGenderDao()

    //                                          Room Contact
    @Singleton
    @Provides
    fun provideRoomContact(@ApplicationContext context: Context): ContactDataBase =
        Room.databaseBuilder(context = context, name = "contact_dbb", klass = ContactDataBase::class.java)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideContactDao(contactDDB: ContactDataBase)=contactDDB.getContactDao()


}