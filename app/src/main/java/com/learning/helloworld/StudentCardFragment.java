package com.learning.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentCardFragment extends Fragment {
    private Student mStudent;

    public StudentCardFragment() {
        // Required empty public constructor
    }
    StudentCardFragment(Student student) {
        mStudent = student;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_student_card, container, false);
        TextView name = (TextView) linearLayout.findViewById(R.id.fragment_student_card_name);
        TextView surname = (TextView) linearLayout.findViewById(R.id.fragment_student_card_surname);
        name.setText(mStudent.getFirstName());
        surname.setText(mStudent.getLastName());
        return linearLayout;
    }
}
