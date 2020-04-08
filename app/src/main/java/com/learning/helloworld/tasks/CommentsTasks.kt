package com.learning.helloworld.tasks

import android.os.AsyncTask
import com.learning.helloworld.structures.Comment
import com.learning.helloworld.structures.CommentsDatabase

class CommentAddTask(
    val db: CommentsDatabase,
    val onLoaded: () -> Unit
) : AsyncTask<Comment, Unit, Unit>() {
    // WORKER THREAD
    override fun doInBackground(vararg params: Comment) {
        db.commentsDao().insert(params[0])
    }

    // UI THREAD
    override fun onPostExecute(result: Unit) {
        onLoaded()
        super.onPostExecute(result)
    }
}

class CommentsLoadTask(
    val db: CommentsDatabase,
    val onLoaded: (List<Comment>) -> Unit
) : AsyncTask<Int, Unit, List<Comment>>() {
    override fun doInBackground(vararg params: Int?) = db.commentsDao().findByReceiver(params[0]!!)

    override fun onPostExecute(result: List<Comment>?) {
        if (result != null) {
            onLoaded(result)
        }
        super.onPostExecute(result)
    }
}