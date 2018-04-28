package com.rukiasoft.medication.preferences

import com.rukiasoft.medication.preferences.MedicationPreferencesConstants.DB_VERSION
import com.rukiasoft.medication.preferences.MedicationPreferencesConstants.FETCHED_DATE_MEDICATION
import javax.inject.Inject

/**
 * Created by Raul on 17/11/2017.
 * Implementation of CodeWarsPreferences
 */
class MedicationPreferencesImpl @Inject constructor(private var preferencesManager: PreferencesManager) : MedicationPreferences {

    override fun clearAllData() {
        preferencesManager.clearAll()
    }

    override fun getFetchMedication() = preferencesManager.getLongFromPreferences(FETCHED_DATE_MEDICATION)

    override fun setFetchMedication(value: Long) {
        preferencesManager.setLongIntoPreferences(FETCHED_DATE_MEDICATION, value)
    }

    override fun deleteFetchMedication() {
        preferencesManager.deleteVarFromSharedPreferences(FETCHED_DATE_MEDICATION)
    }

    override fun getDbVersion() = preferencesManager.getIntFromPreferences(DB_VERSION)

    override fun setDbVersion(value: Int) {
        preferencesManager.setIntIntoPreferences(DB_VERSION, value)
    }

    override fun deleteDbVersion() {
        preferencesManager.deleteVarFromSharedPreferences(DB_VERSION)
    }

}
