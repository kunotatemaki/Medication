package com.rukiasoft.medication.di.components

import com.rukiasoft.medication.MedicationApplicationBase

object MedicationFactory {

    fun component(context: MedicationApplicationBase): MedicationComponent {
        return DaggerMedicationComponent.builder()
                .application(context)
                .build()
    }

}