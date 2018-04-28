package com.rukiasoft.medication.ui.list

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.rukiasoft.medication.databinding.ItemFooterBinding
import com.rukiasoft.medication.databinding.ItemHeaderBinding
import com.rukiasoft.medication.databinding.ItemMedicationBinding
import com.rukiasoft.medication.persistence.entities.MedicationGroup

class MedicationViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindFooter(text: String){
        (binding as ItemFooterBinding).footer.text = text
    }

    fun bindHeader(text: String){
        (binding as ItemHeaderBinding).header.text = text
    }

    fun bindMedication(group: MedicationGroup?, callback: MedicationAdapter.GroupClickCallback?) {
        (binding as ItemMedicationBinding).group = group
        binding.root.setOnClickListener {
            callback?.onClick(binding.group)
        }
        binding.executePendingBindings()
    }
}