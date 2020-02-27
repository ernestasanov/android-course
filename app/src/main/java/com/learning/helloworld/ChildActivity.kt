package com.learning.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
    }
}
