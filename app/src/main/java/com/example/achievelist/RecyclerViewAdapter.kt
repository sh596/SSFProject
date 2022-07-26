package com.example.achievelist

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val data: MutableList<Contacts>,
    private val removeFunction: (Contacts) -> Unit,
    private val readFunction: () -> Int
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        private val checkbox: CheckBox = row.findViewById(R.id.checkBoxRecyclerItem)
        private val content: TextView = row.findViewById(R.id.textViewRecyclerItem)
        private val priorityBar: View = row.findViewById(R.id.priorityBar)
        val deleteBtn: ImageButton = row.findViewById(R.id.deleteButtonRecyclerItem)

        fun onBind(contacts: Contacts){
            content.text = contacts.content
            checkbox.isChecked = contacts.isChecked
            when(contacts.priority){
                0 -> priorityBar.setBackgroundColor(Color.parseColor("#F26363"))
                1 -> priorityBar.setBackgroundColor(Color.parseColor("#EDF263"))
                2 -> priorityBar.setBackgroundColor(Color.parseColor("#6DB674"))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
        holder.deleteBtn.setOnClickListener {
            removeFunction(data[position])
            readFunction()
            data.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = data.size
}