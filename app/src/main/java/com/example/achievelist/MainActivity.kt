package com.example.achievelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    var db : AppDatabase? = null
    var contactsList = mutableListOf<Contacts>()
    val adapter = RecyclerViewAdapter(contactsList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)!!
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)!!
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)!!
        val addBtn = findViewById<Button>(R.id.mainTODoButton)
        var addEdittext = findViewById<EditText>(R.id.mainToDOEdittext)
        val recyclerView : RecyclerView = findViewById(R.id.mainToDoRecyclerView)
        db = AppDatabase.getInstance(this)

        val savedContacts = db!!.contactsDao().getAll()
        recyclerView.layoutManager = LinearLayoutManager(this)

        if(savedContacts.isNotEmpty())
        {
            contactsList.addAll(savedContacts)
        }
        addBtn.setOnClickListener {
            var pr = 1
            if(radioBtn1.isChecked)
                pr = 1
            else if(radioBtn2.isChecked)
                pr = 2
            else if(radioBtn3.isChecked)
                pr = 3
            val contact = Contacts(0,addEdittext.text.toString(),pr,false)
            db?.contactsDao()?.insertAll(contact)
            contactsList.add(contact)
            adapter.notifyDataSetChanged()
        }
        recyclerView.adapter = adapter
    }
}