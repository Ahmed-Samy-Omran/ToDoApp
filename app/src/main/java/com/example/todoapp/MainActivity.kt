package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation: BottomNavigationView
    lateinit var addButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigation=findViewById(R.id.navigation_view)
        addButton=findViewById(R.id.add)
        addButton.setBackgroundResource(R.drawable.circle_floating_btn)
        addButton.setOnClickListener {
//            val intent=Intent(this,UpdateTodoActivity::class.java)
//            startActivity(intent)

            showAddBottomSheet()

        }

        bottomNavigation.setOnItemSelectedListener(
           NavigationBarView.OnItemSelectedListener{item ->
               if (item.itemId==R.id.navigation_list){
                   pushFragment(TodoListFragment())
               }
               if (item.itemId==R.id.navigation_settings){
                   pushFragment(SettingsFragment())
               }

               return@OnItemSelectedListener true
           }
       )
        //that is the id of item selected
        bottomNavigation.selectedItemId=R.id.navigation_list

    }
    // bottom Sheet
    private fun showAddBottomSheet() {
       val addBottomSheet=AddTodoBottomSheet()
        addBottomSheet.show(supportFragmentManager,"")
    }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}