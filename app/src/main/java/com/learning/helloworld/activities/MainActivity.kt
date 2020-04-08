package com.learning.helloworld.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.helloworld.R
import com.learning.helloworld.structures.Student
import com.learning.helloworld.fragments.StudentCardFragment
import com.learning.helloworld.fragments.StudentsFragment
import com.learning.helloworld.activities.LoginActivity.Companion.KEY_LOGIN

class MainActivity : AppCompatActivity() {
    private lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = intent.extras?.get(KEY_LOGIN)?.toString() ?: ""
        val studentsFragment =
            StudentsFragment.newInstance { student ->
                showStudentCard(student)
            }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, studentsFragment)
            .commit()
    }

    private fun showStudentCard(student: Student) {
        val studentCardFragment =
            StudentCardFragment.newInstance(
                student,
                login
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, studentCardFragment)
            .addToBackStack(null)
            .commit()
    }
}
