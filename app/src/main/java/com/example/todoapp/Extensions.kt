package com.example.todoapp

import java.util.Calendar

// Extension function to clear time . i add it to calendar class that is already exist
fun Calendar.clearTime() :Calendar{
    // i want to ignore that 3 factors that i want the object dont carry hour or minute or second so when i select day i want that not affect on it
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
    return this
}