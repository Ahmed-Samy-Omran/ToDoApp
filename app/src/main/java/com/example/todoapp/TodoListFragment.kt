package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.database.MyDataBase
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.Calendar
import java.util.Date

class TodoListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list,container,false)
    }

    lateinit var recyclerView: RecyclerView
    lateinit var calenderView: MaterialCalendarView
    val adapter=TodosRecyclerAdapter(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }      // onResume once user can interact with fragment
        // run when fragment show again to user it run
    override fun onResume() {
        super.onResume()
            getTodoListFromDB()
    }

    //that fun go to DB and return list of todo and show it in adapter
    var calender= Calendar.getInstance()//get current date and prefer calender on date because it has more methodes can use it


     fun getTodoListFromDB() {
       // i want date based on just day not want time
        // calenderTime return time in milliseconds

         //clearTime() that is fun i make it inside Calender
//         if (context==null) return // if the context =null dont do anything
         val todoList=MyDataBase.getInstance(requireContext()).todoDao().getTodosByDate(calender.clearTime().time)
        adapter.changeData(todoList.toMutableList())
    }

    private fun initViews() {
        recyclerView=requireView().findViewById(R.id.todos_recycler)
        recyclerView.adapter=adapter
        calenderView=requireView().findViewById(R.id.calendarView)
        //that make when i enter the program the current day is selected
        calenderView.selectedDate= CalendarDay.today()

        calenderView.setOnDateChangedListener { widget, calederDay, selected ->
           calender.set(Calendar.DAY_OF_MONTH,calederDay.day)
            calender.set(Calendar.MONTH,calederDay.month-1) //month-1 because month start from 0 in Calender class
            calender.set(Calendar.YEAR,calederDay.year)
            getTodoListFromDB() // call it to refresh data
        }


    }
}