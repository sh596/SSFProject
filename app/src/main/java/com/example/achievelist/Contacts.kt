package com.example.achievelist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_contacts")
data class Contacts (
    @PrimaryKey(autoGenerate = true) val id:Long,
    var content :String,
    var priority : Int,
    var isChecked : Boolean)