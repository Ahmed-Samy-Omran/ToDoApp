package com.example.todoapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int?=null,
    @ColumnInfo
    val name:String?=null,
    @ColumnInfo
    val details:String?=null,
    @ColumnInfo
//    @TypeConverters(Converters::class)
    val date: Date?=null,
    @ColumnInfo
    val isDone:Boolean?=false

)