package com.rukiasoft.medication.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rukiasoft.medication.di.interfaces.ViewModelKey
import com.rukiasoft.medication.ui.list.MedicationViewModel
import com.rukiasoft.medication.viewmodel.MedicationViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Raul on 16/11/2017.
 * Module for injection of ViewModels (if needed)
 */
@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MedicationViewModel::class)
    internal abstract fun bindMedicationViewModel(medicationViewModel: MedicationViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: MedicationViewModelFactory): ViewModelProvider.Factory
}