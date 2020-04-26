package com.synbustech.notekeeper.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.synbustech.notekeeper.NOTE_POSITION
import com.synbustech.notekeeper.NoteActivity
import com.synbustech.notekeeper.NoteInfo
import com.synbustech.notekeeper.R

class NoteRecyclerAdapter(private val context: Context, private val notes: List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)

        return ViewHolder(itemView)
    }

    /*override fun getItemCount(): Int {
        return notes.size
    }*/
    //Above commented method and below methods are same
    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.notePosition = position
        holder.txtCourse?.text = note.course?.title
        holder.txtTitle?.text = note.title
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCourse = itemView.findViewById<TextView>(R.id.txtCourse)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)

        var notePosition = 0

        init {
            itemView.setOnClickListener{
                val activityIntent = Intent(context, NoteActivity::class.java)
                activityIntent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(activityIntent)
            }
        }
    }
}