package com.synbustech.notekeeper.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.synbustech.notekeeper.*

class CourseRecyclerAdapter(private val context: Context, private val courses: List<CourseInfo>) :
    RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course_list, parent, false)

        return ViewHolder(itemView)
    }

    /*override fun getItemCount(): Int {
        return notes.size
    }*/
    //Above commented method and below methods are same
    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses[position]
        holder.coursePosition = position
        holder.txtCourse?.text = course.title
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCourse = itemView.findViewById<TextView>(R.id.txtCourse)
        var coursePosition = 0

        init {
            itemView.setOnClickListener{
                Snackbar.make(it, courses[coursePosition]?.title, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}