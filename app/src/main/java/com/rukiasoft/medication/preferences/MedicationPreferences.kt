package com.rukiasoft.medication.preferences

/**
 * Created by Raul on 17/11/2017.
 * Class for accessing the shared preferences in the app
 */
interface MedicationPreferences {

    fun getFetchMedication(): Long
    fun setFetchMedication(value: Long)
    fun deleteFetchMedication()

    fun getDbVersion(): Int
    fun setDbVersion(value: Int)
    fun deleteDbVersion()

    fun clearAllData()
}
