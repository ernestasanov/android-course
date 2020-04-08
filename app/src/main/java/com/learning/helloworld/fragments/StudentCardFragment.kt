package com.learning.helloworld.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.learning.helloworld.R
import com.learning.helloworld.adapters.CommentsAdapter
import com.learning.helloworld.structures.Comment
import com.learning.helloworld.structures.CommentsDatabase
import com.learning.helloworld.structures.Student
import com.learning.helloworld.tasks.CommentAddTask
import com.learning.helloworld.tasks.CommentsLoadTask

class StudentCardFragment(
    private val student: Student,
    private val login: String
) : Fragment() {
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var db: CommentsDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = Room.databaseBuilder(
            context!!,
            CommentsDatabase::class.java, "comments"
        ).build()

        val view = inflater.inflate(R.layout.card, container, false)
        view.findViewById<TextView>(R.id.name).text = student.name

        val commentEdit = view.findViewById<EditText>(R.id.comment)
        view.findViewById<Button>(R.id.post).setOnClickListener {
            val comment = commentEdit.text.toString()
            if (comment.isBlank()) {
                Toast.makeText(context!!, "You have to write something", Toast.LENGTH_SHORT).show()
            } else {
                postComment(login, comment)
                commentEdit.text.clear()
                commentEdit.clearFocus()
            }
        }
        commentsAdapter = CommentsAdapter(emptyList())

        val commentsRecycler = view.findViewById<RecyclerView>(R.id.comments)
        commentsRecycler.layoutManager = LinearLayoutManager(context)
        commentsRecycler.adapter = commentsAdapter

        loadComments()

        return view
    }

    private fun loadComments() {
        CommentsLoadTask(db) { comments ->
            commentsAdapter.comments = comments
            commentsAdapter.notifyDataSetChanged()
        }.execute(student.id)
    }

    private fun postComment(login: String, comment: String) {
        CommentAddTask(db) {
            loadComments()
        }.execute(Comment(student.id, login, comment))
    }

    companion object {
        fun newInstance(student: Student, login: String) =
            StudentCardFragment(student, login)
    }
}
