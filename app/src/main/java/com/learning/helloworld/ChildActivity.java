package com.learning.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

class ChildActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
    }
}
