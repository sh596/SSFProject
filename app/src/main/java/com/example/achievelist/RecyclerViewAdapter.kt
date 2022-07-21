package com.example.achievelist

import android.app.Application
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val data : List<Contacts>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(val row : View) : RecyclerView.ViewHolder(row){
        val checkbox : CheckBox = row.findViewById(R.id.checkBoxRecyclerItem)
        val content : TextView = row.findViewById(R.id.textViewRecyclerItem)
        val priorityBar : View = row.findViewById(R.id.priorityBar)
        val progressBar : ProgressBar = row.findViewById(R.id.progressBar)
        var progressBarEdittext : EditText = row.findViewById(R.id.progressBarEdittext)
        val deleteBtn : ImageButton = row.findViewById(R.id.deleteButtonRecyclerItem)
        val deleteView : View = row.findViewById(R.id.deleteView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.content.text = data.get(position).content
        holder.deleteBtn.setOnClickListener(View.OnClickListener {
            holder.deleteView.setVisibility(View.VISIBLE)
            holder.progressBarEdittext.setText("")
        })
        holder.progressBarEdittext.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                val value = holder.progressBarEdittext.text.toString().toIntOrNull() ?: 0
                if(value != null)
                {
                    if(value>100)
                    {
                        holder.progressBar.setProgress(100)
                    }
                    else
                    {
                        holder.progressBar.setProgress(value)
                    }
                }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        when(data.get(position).priority){
            1 -> holder.priorityBar.setBackgroundColor(Color.parseColor("#F26363"))
            2 -> holder.priorityBar.setBackgroundColor(Color.parseColor("#EDF263"))
            3 -> holder.priorityBar.setBackgroundColor(Color.parseColor("#6DB674"))
            else -> holder.priorityBar.setBackgroundColor(Color.parseColor("#F26363"))
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
}