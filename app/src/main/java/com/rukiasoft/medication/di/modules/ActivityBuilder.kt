package com.rukiasoft.medication.di.modules

import com.rukiasoft.medication.di.interfaces.CustomScopes
import com.rukiasoft.medication.ui.list.MedicationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Raul on 16/11/2017.
 * Builder for activities
 */
@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMedicationScreen(): MedicationActivity


}