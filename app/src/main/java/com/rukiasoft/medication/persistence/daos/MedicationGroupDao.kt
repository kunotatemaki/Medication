package com.rukiasoft.medication.persistence.daos

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.RoomWarnings
import com.rukiasoft.medication.persistence.entities.MedicationGroup

@Dao
abstract class MedicationGroupDao : BaseDao<MedicationGroup> {

    @Query("SELECT * FROM medication_group ORDER BY letter ASC")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    protected abstract fun getListMedicationsInternal(): LiveData<List<MedicationGroup>>


    fun getListMedications(): LiveData<List<MedicationGroup>> =
            getListMedicationsInternal()


    @Query("DELETE FROM medication_group")
    abstract fun deleteAll()


}