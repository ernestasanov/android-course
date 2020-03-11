package com.learning.helloworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class StudentsFragment : Fragment() {
    private var studentsView: RecyclerView? = null
    private var onStudentClick : ((Student) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students, container, false)

        val studentsFile = File(context?.filesDir, "students.txt")
        studentsFile.delete()
        val studentsData = StudentLoader.loadStudents(studentsFile)

        studentsView = view.findViewById(R.id.students)
        studentsView?.layoutManager = LinearLayoutManager(context)
        studentsView?.adapter = StudentsAdapter(studentsData) { student ->
            onStudentClick?.invoke(student)
        }

        return view
    }

    companion object {
        fun newInstance(onStudentClick: (Student) -> Unit): StudentsFragment {
            val fragment = StudentsFragment()
            fragment.onStudentClick = onStudentClick
            return fragment
        }
    }
}