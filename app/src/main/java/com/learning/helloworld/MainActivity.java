package com.learning.helloworld;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentsFragment studentsFragment = StudentsFragment.newInstance(new StudentsFragment.StudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                showStudentCard(student);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, studentsFragment)
                .commit();
    }

    private void showStudentCard(Student student) {
        StudentCardFragment studentCardFragment = StudentCardFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, studentCardFragment)
                .addToBackStack(null)
                .commit();
    }
}
