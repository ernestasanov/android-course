package com.learning.helloworld.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.helloworld.R

class ChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
    }
}
