package com.rukiasoft.medication.repository


import android.arch.lifecycle.LiveData
import com.rukiasoft.codewars.network.ApiResponse
import com.rukiasoft.codewars.network.NetworkBoundResource
import com.rukiasoft.medication.persistence.utils.PersistenceUtils
import com.rukiasoft.codewars.vo.Resource
import com.rukiasoft.medication.AppExecutors
import com.rukiasoft.medication.network.FakeServer
import com.rukiasoft.medication.persistence.PersistenceManager
import com.rukiasoft.medication.preferences.MedicationPreferences
import com.rukiasoft.medication.utils.DateUtils
import com.rukiasoft.medication.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 4/10/17.
 * Repository that handles Task instances.
 *
 * Task - value object name
 * Repository - type of this class.
 */

@Singleton
class MedicationRequests @Inject
constructor(
        private val appExecutors: AppExecutors,
        private val persistenceManager: PersistenceManager,
        private val preferences: MedicationPreferences,
        private val fakeServer: FakeServer
) {
    //request info from user if the last request is more than 15 minutes old
    private val rateLimit: RateLimiter = RateLimiter(15, TimeUnit.MINUTES)

    fun downloadMedications(): LiveData<Resource<Int>> {

        return object : NetworkBoundResource<Int, MedicationServerResponse>(appExecutors) {
            override fun saveCallResult(item: MedicationServerResponse) {
                //store data in db
                val medications = PersistenceUtils.getMedicationsFromServerResponse(item)
                //get the number of medications in the db before inserting
                val before = persistenceManager.getNumberOfMedicationsNow()
                //insert
                persistenceManager.insertMedication(medications)
                //get the number after the insertion
                val after = persistenceManager.getNumberOfMedicationsNow()
                //store groups if there are new medications in the db
                if (after > before) {
                    val groups = PersistenceUtils.getMedicationsGrouped(medications)
                    persistenceManager.insertMedicationGroups(groups)
                }

                //store the fetched timestamp
                preferences.setFetchMedication(DateUtils.currentTime.time)

            }

            override fun shouldFetch(data: Int?): Boolean {
                return data == null || data == 0 || rateLimit.shouldFetch(preferences.getFetchMedication())
            }

            override fun loadFromDb(): LiveData<Int> {
                return persistenceManager.getNumberOfMedications()
            }

            override fun createCall(): LiveData<ApiResponse<MedicationServerResponse>> {
                //create call
                return fakeServer.getMedications()
            }

            override fun onFetchFailed() {
                //something went wrong
            }
        }.asLiveData()
    }


}