package com.learning.helloworld.structures

import androidx.room.*

@Entity
data class Comment(
    @ColumnInfo(name = "receiver") val receiver: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "text") val text: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

@Dao
interface CommentsDao {
    @Query("SELECT * FROM comment")
    fun getAll(): List<Comment>

    @Query("SELECT * FROM comment WHERE receiver = :receiver ORDER BY id DESC")
    fun findByReceiver(receiver: Int): List<Comment>

    @Insert
    fun insert(comment: Comment)

    @Delete
    fun delete(comment: Comment)
}

@Database(entities = arrayOf(Comment::class), version = 1)
abstract class CommentsDatabase : RoomDatabase() {
    abstract fun commentsDao(): CommentsDao
}