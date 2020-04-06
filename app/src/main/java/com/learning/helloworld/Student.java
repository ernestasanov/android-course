package com.learning.helloworld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    String firstName;

    String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class StudentLoader {
    static List<Student> loadStudents(File file) {
        if (!file.exists()) {
            List<Student> students = generateStudents();
            saveStudents(students, file);
            return students;
        }
        List<String> lines = readLines(file);
        List<Student> students = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty()) {
                students.add(loadStudent(line));
            }
        }
        return students;
    }

    static private List<String> readLines(File file) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                String line = reader.readLine();
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    static private Student loadStudent(String str) {
        String[] parts = str.split(":");
        return new Student(parts[0], parts[1]);
    }

    static private List<Student> generateStudents() {
        List<Student> students = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            students.add(new Student("имя"+i, "фамилия"+i));
        }
        return students;
    }

    static private void saveStudents(List<Student> students, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("");
            for (Student student : students) {
                String line = saveStudent(student);
                writer.append(line);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private String saveStudent(Student student) {
        return student.firstName + ":" + student.lastName;
    }
}