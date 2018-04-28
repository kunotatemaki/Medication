package com.rukiasoft.medication.persistence.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.RoomWarnings
import com.rukiasoft.medication.persistence.entities.Medication
import com.rukiasoft.medication.utils.getDistinct

@Dao
abstract class MedicationDao : BaseDao<Medication> {


    @Query("SELECT COUNT(DISTINCT name) FROM medication")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    abstract fun getNumberOfMedicationsNow(): Int

    @Query("SELECT COUNT(DISTINCT name) FROM medication")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    protected abstract fun getNumberOfMedicationsInternal(): LiveData<Int>


    fun getNumberOfMedications(): LiveData<Int> =
            getNumberOfMedicationsInternal().getDistinct()

    @Query("DELETE FROM medication")
    abstract fun deleteAll()


}