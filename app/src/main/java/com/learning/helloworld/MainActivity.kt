package com.learning.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentsFragment = StudentsFragment.newInstance { student ->
            showStudentCard(student)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, studentsFragment)
            .commit()
    }

    private fun showStudentCard(student: Student) {
        val studentCardFragment = StudentCardFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, studentCardFragment)
            .addToBackStack(null)
            .commit()
    }
}
