package com.rukiasoft.medication.persistence.utils

import com.rukiasoft.medication.persistence.entities.Medication
import com.rukiasoft.medication.persistence.entities.MedicationGroup
import com.rukiasoft.medication.repository.MedicationServerResponse


object PersistenceUtils {
    fun getMedicationsFromServerResponse(server: MedicationServerResponse): MutableList<Medication> {
        val list: MutableList<Medication> = mutableListOf()
        list.addAll(server.medications.map { Medication(it) })
        return list
    }

    fun getMedicationsGrouped(medications: MutableList<Medication>): MutableList<MedicationGroup> {
        val groups = hashMapOf<Char, Int>()
        medications.forEach {
            val firstLetter: Char = it.name.first().toUpperCase()
            //exists -> increment
            val value = groups[firstLetter]
            groups[firstLetter] = (value ?: 0) + 1

        }
        val medicationGroup: MutableList<MedicationGroup> = mutableListOf()
        val iterator = groups.entries.iterator()
        while (iterator.hasNext()){
            val pair = iterator.next()
            medicationGroup.add(MedicationGroup(pair.key.toString(), pair.value))
        }
        return medicationGroup
    }


}