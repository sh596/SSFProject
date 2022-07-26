package com.example.achievelist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_contacts")
class Contacts (
    var content :String,
    var priority : Int,
    var isChecked : Boolean
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}