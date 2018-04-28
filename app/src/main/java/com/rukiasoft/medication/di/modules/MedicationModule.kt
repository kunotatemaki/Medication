package com.rukiasoft.medication.di.modules

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.rukiasoft.medication.MedicationApplicationBase
import com.rukiasoft.medication.persistence.PersistenceManager
import com.rukiasoft.medication.persistence.PersistenceManagerImpl
import com.rukiasoft.medication.persistence.db.MedicationDatabase
import com.rukiasoft.medication.preferences.MedicationPreferences
import com.rukiasoft.medication.preferences.MedicationPreferencesImpl
import com.rukiasoft.medication.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Raul on 16/11/2017.
 * Module for the app component
 */
@Module(includes = [(ViewModelModule::class)])
@Singleton
class MedicationModule {


    @Provides
    fun providesContext(application: MedicationApplicationBase): Context {
        return application.applicationContext
    }

    @Provides
    fun providesPersistenceManager(persistenceManager: PersistenceManagerImpl): PersistenceManager {
        return persistenceManager
    }

    @Provides
    fun providesPreferencesManager(medicationPreferences: MedicationPreferencesImpl): MedicationPreferences {
        return medicationPreferences
    }

    @Singleton
    @Provides
    fun provideDb(app: MedicationApplicationBase, preferences: MedicationPreferences): MedicationDatabase {


        return Room.databaseBuilder(app,
                MedicationDatabase::class.java, Constants.DATABASE_NAME)
                //.addMigrations()    //no migrations, version 1
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        //if the db is updated, remove info from preferences (as we are recreating all the tables)
                        val oldVersion = preferences.getDbVersion()
                        if (oldVersion != db.version) {
                            //store the new version
                            preferences.setDbVersion(db.version)
                        }
                    }
                })
                .build()

    }


}