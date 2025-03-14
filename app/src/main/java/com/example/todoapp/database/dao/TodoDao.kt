package com.example.todoapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.database.model.Todo
import java.util.Date

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)
    @Update
    fun updateTodo(todo: Todo)
    @Delete
    fun deleteTodo(todo: Todo)

    @Query("select * from Todo")
    fun getAllTodos():MutableList<Todo>

    //select todo based on date i choose on calender
    //date is parameter of function getTodosByDate
    @Query("select * from Todo where date=:date")
    fun getTodosByDate(date: Date):List<Todo>

}