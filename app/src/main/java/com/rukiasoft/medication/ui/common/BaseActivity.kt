package com.rukiasoft.medication.ui.common

import android.arch.lifecycle.ViewModelProvider
import com.rukiasoft.medication.utils.ResourcesManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory


    @Inject
    protected lateinit var resourcesManager: ResourcesManager
}