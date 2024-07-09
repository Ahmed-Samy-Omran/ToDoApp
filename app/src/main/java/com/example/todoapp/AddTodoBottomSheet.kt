package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.example.todoapp.database.MyDataBase
import com.example.todoapp.database.model.Todo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class AddTodoBottomSheet:BottomSheetDialogFragment() {
    lateinit var titleLayout: TextInputLayout
    lateinit var detailsLayout: TextInputLayout
    lateinit var chooseDate: TextView
    lateinit var addTodo: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        titleLayout=requireView().findViewById(R.id.title_layout)
        detailsLayout=requireView().findViewById(R.id.details_layout)
        chooseDate=requireView().findViewById(R.id.ChooseDate)
        addTodo=requireView().findViewById(R.id.add)
        chooseDate.setOnClickListener {
            showDatePicker()
        }
        // i make the choosedate by deafult the current date
        chooseDate.setText(""+calendar.get(Calendar.DAY_OF_MONTH)
                +"/"+(calendar.get(Calendar.MONTH)+1)
                +"/"+ calendar.get(Calendar.YEAR),

            )

        addTodo.setOnClickListener {
            if (validateForm()){
               //form is valid and insert Todo item
                val title=titleLayout.editText?.text.toString()
                val details=detailsLayout.editText?.text.toString()
                //now i bring the title and details from editText now i want to insert it to database
                insertToDo(title,details)

            }

        }
                                    // i decide which i want
        //activity ->nullable                       context ->nullable
        //requireActivity() ->not nullable                requireContext() ->not nullable

    }

     fun insertToDo(title: String, details: String) {
            val todo= Todo(
                name = title,
                details = details,                 // calendar.time it get all date from calender
                date = calendar.time)             //applicationContext that the context of full app
         MyDataBase.getInstance(requireContext().applicationContext).todoDao().addTodo(todo)
         Toast.makeText(requireContext(),"todo added",Toast.LENGTH_LONG).show()

         //i should close bottomSheet
         dismiss()
    }

    // this fun check if the form is valid or not and if it is valid it will return true and if not it will return false and error message
    fun validateForm():Boolean{
        var isValid=true
        if (titleLayout.editText?.text.toString().isBlank()){

            titleLayout.error="please enter todo title"
            isValid=false
        }else{
            titleLayout.error=null

        }
        if (detailsLayout.editText?.text.toString().isBlank()){

            detailsLayout.error="please enter todo details"
            isValid=false

        }else{
            detailsLayout.error=null

        }
        return isValid
    }


    // take obj from calender to get the current date and day and and i set it all and it carry them
    val calendar=Calendar.getInstance()
    fun showDatePicker(){

        val datePicker= DatePickerDialog(requireContext(),     //(month+1) because the month start from 0
            { view, year, month, day ->
                calendar.set(Calendar.DAY_OF_MONTH,day)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.YEAR,year)
                chooseDate.setText(""+ day + "/" + (month+1)  + "/" + year)},
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
            )
        datePicker.show()
    }

}