package com.learning.helloworld;

import android.os.AsyncTask;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class StudentsLoadTask extends AsyncTask<File, Void, List<Student>> {
    private WeakReference<UIListener> listener;

    StudentsLoadTask(WeakReference<UIListener> listener) {
        this.listener = listener;
    }

    interface UIListener {
        void onStudentsLoaded(List<Student> students);
    }

    // WORKER THREAD
    @Override
    protected List<Student> doInBackground(File... files) {
        if (files.length == 0) {
            return new ArrayList<>();
        }
        File file = files[0];
        return StudentLoader.loadStudents(file);
    }

    // UI THREAD
    @Override
    protected void onPostExecute(List<Student> students) {
        listener.get().onStudentsLoaded(students);
        super.onPostExecute(students);
    }
}