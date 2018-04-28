package com.rukiasoft.medication.ui.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rukiasoft.medication.R
import com.rukiasoft.medication.databinding.GlideBindingComponent
import com.rukiasoft.medication.databinding.ItemFooterBinding
import com.rukiasoft.medication.databinding.ItemHeaderBinding
import com.rukiasoft.medication.databinding.ItemMedicationBinding
import com.rukiasoft.medication.persistence.entities.MedicationGroup

class MedicationAdapter constructor(private val header: String, private val footer: String, private val challengeAuthoredClickCallback: MedicationAdapter.GroupClickCallback) :
        RecyclerView.Adapter<MedicationViewHolder>() {

    enum class Type(val value: Int) {
        HEADER(0),
        REGULAR(1),
        FOOTER(2)
    }

    private var items: MutableList<MedicationGroup> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            Type.HEADER.value -> {

                DataBindingUtil.inflate<ItemHeaderBinding>(inflater, R.layout.item_header, parent,
                        false, GlideBindingComponent())!!
            }
            Type.FOOTER.value -> {
                DataBindingUtil.inflate<ItemFooterBinding>(inflater, R.layout.item_footer, parent,
                        false, GlideBindingComponent())!!
            }
            else -> {
                DataBindingUtil.inflate<ItemMedicationBinding>(inflater, R.layout.item_medication, parent,
                        false, GlideBindingComponent())!!
            }
        }

        return MedicationViewHolder(binding)

    }

    fun setItems(list: List<MedicationGroup>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (items.size == 0) {
            0
        } else {
            items.size + 2
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> Type.HEADER.value
            items.size + 2 - 1 -> Type.FOOTER.value
            else -> Type.REGULAR.value
        }
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        when (position) {
            0 -> holder.bindHeader(header)
            items.size + 2 - 1 -> holder.bindFooter(footer)
            else -> {
                val group: MedicationGroup? = items[position - 1]
                holder.bindMedication(group, challengeAuthoredClickCallback)
            }
        }

    }

    interface GroupClickCallback {
        fun onClick(group: MedicationGroup?)
    }


}