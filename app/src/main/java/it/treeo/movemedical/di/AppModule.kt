package it.treeo.movemedical.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.treeo.movemedical.database.AppDataBase
import it.treeo.movemedical.util.Constants

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, Constants.DB_NAME
    ).allowMainThreadQueries().build()
}