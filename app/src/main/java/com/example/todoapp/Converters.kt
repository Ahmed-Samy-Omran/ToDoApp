package com.example.todoapp

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    //create all that to handle with date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}