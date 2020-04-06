package com.learning.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class StudentViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;
    private Student student;
    private StudentsFragment.StudentClickListener studentClickListener;

    public StudentViewHolder(@NonNull View itemView, StudentsFragment.StudentClickListener studentClickListener) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.name);
        this.studentClickListener = studentClickListener;
    }

    void bind(Student student) {
        this.student = student;
        nameTextView.setText(student.firstName + " " + student.lastName);
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentClickListener.onStudentClicked(StudentViewHolder.this.student);
            }
        });
    }
}

public class StudentsAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    List<Student> students;
    private StudentsFragment.StudentClickListener studentClickListener;

    StudentsAdapter(List<Student> students, StudentsFragment.StudentClickListener studentClickListener) {
        this.students = students;
        this.studentClickListener = studentClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.student, parent, false);
        return new StudentViewHolder(itemView, studentClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}