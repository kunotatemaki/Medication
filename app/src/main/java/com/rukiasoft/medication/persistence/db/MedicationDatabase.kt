package com.rukiasoft.medication.persistence.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.rukiasoft.medication.persistence.daos.MedicationDao
import com.rukiasoft.medication.persistence.daos.MedicationGroupDao
import com.rukiasoft.medication.persistence.entities.Medication
import com.rukiasoft.medication.persistence.entities.MedicationGroup
import com.rukiasoft.medication.persistence.utils.Converters

@Database(entities = [(MedicationGroup::class), (Medication::class)],
        version = 1)
@TypeConverters(Converters::class)
abstract class MedicationDatabase : RoomDatabase() {
    abstract fun medicationGroupDao(): MedicationGroupDao
    abstract fun medicationDao(): MedicationDao
}
