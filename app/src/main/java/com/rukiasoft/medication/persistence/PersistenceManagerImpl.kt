package com.rukiasoft.medication.persistence

import android.arch.lifecycle.LiveData
import com.rukiasoft.medication.persistence.db.MedicationDatabase
import com.rukiasoft.medication.persistence.entities.Medication
import com.rukiasoft.medication.persistence.entities.MedicationGroup
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(private val db: MedicationDatabase) : PersistenceManager {


    override fun insertMedicationGroups(medicationGroups: MutableList<MedicationGroup>) {
        db.medicationGroupDao().insert(medicationGroups)
    }

    override fun getMedicationGroups(): LiveData<List<MedicationGroup>>{
        return db.medicationGroupDao().getListMedications()
    }

    override fun insertMedication(medication: MutableList<Medication>) {
        db.medicationDao().insert(medication)
    }

    override fun getNumberOfMedications() = db.medicationDao().getNumberOfMedications()

    override fun getNumberOfMedicationsNow() = db.medicationDao().getNumberOfMedicationsNow()

    override fun deleteDb() {
        db.medicationGroupDao().deleteAll()
    }

}
