package com.example.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.Converters
import com.example.todoapp.database.dao.TodoDao
import com.example.todoapp.database.model.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDataBase:RoomDatabase(){
    abstract fun todoDao(): TodoDao

    companion object{
        private val DATABASE_NAME="todo-DataBase"
         private var myDataBase:MyDataBase?=null


        fun getInstance(context: Context): MyDataBase {
            //single object from database (Singleton pattern)
            if (myDataBase==null){
                myDataBase=   Room.databaseBuilder(context,MyDataBase::class.java,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return myDataBase!!

        }
    }

}