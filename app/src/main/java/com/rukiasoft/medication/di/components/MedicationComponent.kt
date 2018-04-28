package com.rukiasoft.medication.di.components

import com.rukiasoft.medication.MedicationApplicationBase
import com.rukiasoft.medication.di.modules.ActivityBuilder
import com.rukiasoft.medication.di.modules.FragmentsProvider
import com.rukiasoft.medication.di.modules.MedicationModule
import com.rukiasoft.medication.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main component of the student-planner app
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ActivityBuilder::class), (MedicationModule::class),
    (FragmentsProvider::class), (FragmentsProvider::class),
    (ViewModelModule::class)])
interface MedicationComponent : AndroidInjector<MedicationApplicationBase>  {

    override fun inject(medicationApp: MedicationApplicationBase)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MedicationApplicationBase): Builder

        fun build(): MedicationComponent
    }

}