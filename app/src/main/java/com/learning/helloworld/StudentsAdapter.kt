package com.learning.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class StudentViewHolder(
    itemView: ConstraintLayout,
    val onClick : (Student) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var nameTextView : TextView? = itemView.findViewById(R.id.name)

    fun bind(student: Student) {
        nameTextView?.text = "${student.firstName} ${student.lastName}"
        nameTextView?.setOnClickListener {
            onClick(student)
        }
    }
}

class StudentsAdapter(
    private val students: List<Student>,
    private val onClick : (Student) -> Unit
) : RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.student, parent, false) as ConstraintLayout
        return StudentViewHolder(itemView, onClick)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

}