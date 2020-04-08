package com.learning.helloworld.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.learning.helloworld.R
import com.learning.helloworld.structures.Comment
import com.learning.helloworld.structures.Student

class CommentViewHolder(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView) {
    private val authorTextView: TextView = itemView.findViewById(R.id.author)
    private val commentTextView: TextView = itemView.findViewById(R.id.comment_text)

    fun bind(comment: Comment) {
        authorTextView.text = comment.author
        commentTextView.text = comment.text
    }
}

class CommentsAdapter(var comments: List<Comment>) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.comment, parent, false) as ConstraintLayout
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

}