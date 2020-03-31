package com.learning.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class StudentsFragment extends Fragment {
    private RecyclerView studentsView;

    public interface StudentClickListener {
        void onStudentClicked(Student student);
    }

    private StudentClickListener onStudentClickListener;
    private List<Student> students = new ArrayList<>();

    private StudentsAdapter studentsAdapter;

    private StudentsLoadTask studentsLoadTask;

    private StudentsLoadTask.UIListener listener;

    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        File studentsFile = new File(requireContext().getFilesDir(), "students.txt");
        listener = new StudentsLoadTask.UIListener() {
            @Override
            public void onStudentsLoaded(@NonNull List<Student> students) {
                studentsAdapter.students = students;
                studentsAdapter.notifyDataSetChanged();
            }
        };

        studentsLoadTask = new StudentsLoadTask(new WeakReference<>(listener));
        studentsLoadTask.execute(studentsFile);

        studentsView = view.findViewById(R.id.students);
        studentsView.setLayoutManager(new LinearLayoutManager(getContext()));
        studentsAdapter = new StudentsAdapter(students, new StudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                onStudentClickListener.onStudentClicked(student);
            }
        });
        studentsView.setAdapter(studentsAdapter);

        return view;
    }

    public void onDestroy() {
        studentsLoadTask.cancel(true);
        super.onDestroy();
    }

    static StudentsFragment newInstance(StudentClickListener studentClickListener) {
        StudentsFragment fragment = new StudentsFragment();
        fragment.onStudentClickListener = studentClickListener;
        return fragment;
    }
}