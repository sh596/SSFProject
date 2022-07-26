package com.example.achievelist

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var db: AppDatabase? = null
    private var contactsList = mutableListOf<Contacts>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)!!
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)!!
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)!!
        val addBtn = findViewById<Button>(R.id.mainTODoButton)
        var addEdittext = findViewById<EditText>(R.id.mainToDOEdittext)
        val recyclerView: RecyclerView = findViewById(R.id.mainToDoRecyclerView)
        db = AppDatabase.getInstance(this)


        val removeFunction = { c: Contacts -> db!!.contactsDao().delete(c) }

        val readFunction = { Log.d(TAG, "onCreate: ${db!!.contactsDao().getAll().size }")}
        val adapter = RecyclerViewAdapter(contactsList, removeFunction, readFunction)

        val savedContacts = db!!.contactsDao().getAll()
        recyclerView.layoutManager = LinearLayoutManager(this)


        recyclerView.adapter = adapter
        if (savedContacts.isNotEmpty()) {
            contactsList.addAll(savedContacts)
        }
        addBtn.setOnClickListener {
            var pr = 3
            if (radioBtn1.isChecked)
                pr = 0
            else if (radioBtn2.isChecked)
                pr = 1
            else if (radioBtn3.isChecked)
                pr = 2
            val contact = Contacts(addEdittext.text.toString(), pr, false)
            runBlocking {
                db!!.contactsDao().insertAll(contact)
            }
            contactsList.clear()
            runBlocking {
                contactsList.addAll(db!!.contactsDao().getAll())
            }
            adapter.notifyDataSetChanged()
        }
    }
}