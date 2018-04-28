package com.rukiasoft.medication.persistence.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Roll on 21/11/17.
 * Entity for User Info
 */

@Entity(tableName = "medication", indices = [(Index(value = arrayOf("name"), unique = true))])
class Medication constructor(@PrimaryKey
                             @ColumnInfo(name = "name")
                             var name: String
) {

    fun compareTo(user: Medication): Boolean {
        return (name == user.name)
    }

}