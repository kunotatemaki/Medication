package com.rukiasoft.medication.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rukiasoft.codewars.vo.Status
import com.rukiasoft.medication.R
import com.rukiasoft.medication.databinding.ActivityMedicationBinding
import com.rukiasoft.medication.databinding.GlideBindingComponent
import com.rukiasoft.medication.persistence.entities.MedicationGroup
import com.rukiasoft.medication.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_medication.*
import timber.log.Timber


class MedicationActivity : BaseActivity() {

    private lateinit var viewModel: MedicationViewModel
    private lateinit var mBinding: ActivityMedicationBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var header: String
    private lateinit var footer: String

    private var menuItem: MenuItem? = null
    protected var isRefreshing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MedicationViewModel::class.java)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_medication, GlideBindingComponent())

        mRecyclerView = mBinding.content.list

        header = resourcesManager.getString(R.string.header)
        footer = resourcesManager.getString(R.string.footer)

        setUpRecycler()

        setSupportActionBar(toolbar)

        viewModel.numberOfMedications.observe(this, Observer {
            it?.let {
                when (it.status) {

                    Status.SUCCESS -> {
                        hideRefresh()
                    }
                    Status.ERROR -> {
                        hideRefresh()
                    }
                    Status.LOADING -> {
                        showRefresh()
                    }
                }
            }
        })

        //Listen for users
        viewModel.medicationGroups.observe(this, Observer {
            it?.let {
                (mRecyclerView.adapter as MedicationAdapter).setItems(it)
            }
        })


    }

    private fun setUpRecycler() {

        //get the recycler
        mRecyclerView = mBinding.content.list

        // use a linear
        // layout manager
        val mLayoutManager = LinearLayoutManager(this.applicationContext, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = mLayoutManager

        //add a divider decorator
        val dividerItemDecoration = DividerItemDecoration(mRecyclerView.context,
                DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(dividerItemDecoration)


        //add the adapter
        val adapter = MedicationAdapter(header, footer, object : MedicationAdapter.GroupClickCallback {
            override fun onClick(group: MedicationGroup?) {
                group?.letter?.let {
                    Toast.makeText(this@MedicationActivity.applicationContext, it, Toast.LENGTH_SHORT).show()
                }
            }
        })

        mRecyclerView.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        menuItem = menu.findItem(R.id.action_refresh)
        if(isRefreshing){
            isRefreshing = false
            showRefresh()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_refresh) {
            if (isRefreshing.not()) {
                //force refresh
                viewModel.refreshData()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRefresh() {

        if (isRefreshing.not()) {
            isRefreshing = true
            //set ProgressBar in the MenuItem
            menuItem?.setActionView(R.layout.menu_item_refresh)

        }
    }

    private fun hideRefresh() {
        isRefreshing = false
        menuItem?.actionView = null

    }
}
