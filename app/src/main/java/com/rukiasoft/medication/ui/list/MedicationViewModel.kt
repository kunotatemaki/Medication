package com.rukiasoft.medication.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.preference.PreferenceManager
import com.rukiasoft.codewars.vo.Resource
import com.rukiasoft.medication.persistence.PersistenceManager
import com.rukiasoft.medication.persistence.entities.MedicationGroup
import com.rukiasoft.medication.preferences.MedicationPreferences
import com.rukiasoft.medication.repository.MedicationRequests
import com.rukiasoft.medication.utils.switchMap
import javax.inject.Inject


class MedicationViewModel @Inject constructor(private val medicationRequests: MedicationRequests,
                                              persistenceManager: PersistenceManager,
                                              private val preferenceManager: MedicationPreferences) : ViewModel() {

    private val query = MutableLiveData<Long>()

    val medicationGroups: LiveData<List<MedicationGroup>> = persistenceManager.getMedicationGroups()

    val numberOfMedications: LiveData<Resource<Int>>

    init {
        query.value = System.currentTimeMillis()
        numberOfMedications = query.switchMap { _ ->
            medicationRequests.downloadMedications()
        }
    }

    fun refreshData() {
        //delete date to force download
        preferenceManager.deleteFetchMedication()
        query.value = System.currentTimeMillis()
    }


}