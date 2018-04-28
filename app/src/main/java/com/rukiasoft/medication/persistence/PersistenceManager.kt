package com.rukiasoft.medication.persistence

import android.arch.lifecycle.LiveData
import com.rukiasoft.medication.persistence.entities.Medication
import com.rukiasoft.medication.persistence.entities.MedicationGroup

interface PersistenceManager {

    fun insertMedicationGroups(medicationGroups: MutableList<MedicationGroup>)
    fun getMedicationGroups(): LiveData<List<MedicationGroup>>

    fun insertMedication(medication: MutableList<Medication>)
    fun getNumberOfMedications(): LiveData<Int>
    fun getNumberOfMedicationsNow(): Int
    fun deleteDb()

}