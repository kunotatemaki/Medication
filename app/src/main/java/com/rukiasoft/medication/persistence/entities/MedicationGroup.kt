package com.rukiasoft.medication.persistence.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Roll on 21/11/17.
 * Entity for User Info
 */

@Entity(tableName = "medication_group", indices = [(Index(value = arrayOf("letter"), unique = true))])
class MedicationGroup constructor(@PrimaryKey
                                  @ColumnInfo(name = "letter")
                                  var letter: String,
                                  @ColumnInfo(name = "matches")
                                  var matches: Int
) {

    fun compareTo(user: MedicationGroup): Boolean {
        return (letter == user.letter)
                .and(matches == user.matches)
    }

    fun getComposedText() = "$letter ($matches)"
}