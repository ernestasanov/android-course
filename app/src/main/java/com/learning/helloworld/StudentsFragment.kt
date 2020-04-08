package com.learning.helloworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.lang.ref.WeakReference

class StudentsFragment(private val onStudentClick: (Student) -> Unit) : Fragment() {
    private lateinit var studentsView: RecyclerView
    private val students = emptyList<Student>()

    private lateinit var studentsAdapter: StudentsAdapter

    private lateinit var studentsLoadTask: StudentsLoadTask

    private lateinit var listener: StudentsLoadTask.UIListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students, container, false)

        val studentsFile = File(context?.filesDir, "students.txt")
        listener = object : StudentsLoadTask.UIListener {
            override fun onStudentsLoaded(students: List<Student>) {
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                studentsAdapter.students = students
                studentsAdapter.notifyDataSetChanged()
            }
        }

        studentsLoadTask = StudentsLoadTask(WeakReference(listener))
        studentsLoadTask.execute(studentsFile)

        studentsView = view.findViewById(R.id.students)
        studentsView.layoutManager = LinearLayoutManager(context)
        studentsAdapter = StudentsAdapter(students) { student ->
            onStudentClick.invoke(student)
        }
        studentsView.adapter = studentsAdapter

        return view
    }

    override fun onDestroy() {
        studentsLoadTask.cancel(true)
        super.onDestroy()
    }

    companion object {
        fun newInstance(onStudentClick: (Student) -> Unit) = StudentsFragment(onStudentClick)
    }
}