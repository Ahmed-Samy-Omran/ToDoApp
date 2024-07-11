package com.example.todoapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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
    val todolistFragment=TodoListFragment()
    val settingsFragment=SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigation=findViewById(R.id.navigation_view)
        addButton=findViewById(R.id.add)
        addButton.setBackgroundResource(R.drawable.circle_floating_btn)
        addButton.setOnClickListener {

            // Add animation
            val scaleX = ObjectAnimator.ofFloat(addButton, "scaleX", 1f, 1.2f, 1f)
            val scaleY = ObjectAnimator.ofFloat(addButton, "scaleY", 1f, 1.2f, 1f)
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY)
            animatorSet.duration = 500
            animatorSet.start()

            // Call the function to show the bottom sheet
            showAddBottomSheet()


        }

        bottomNavigation.setOnItemSelectedListener(
           NavigationBarView.OnItemSelectedListener{item ->
               if (item.itemId==R.id.navigation_list){
                   pushFragment(todolistFragment) // instead of every time push fragment  and create new obj i just call it
               }
               if (item.itemId==R.id.navigation_settings){
                   pushFragment(settingsFragment)
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
        addBottomSheet.onTodoAddedListener=object :AddTodoBottomSheet.OnTodoAddedListener{
            override fun onTodoAdded() {
                // i want to refresh the list from DB inside ListFragment
                // if fun(list fun) is visible call it only
                if (todolistFragment.isVisible){
                    todolistFragment.getTodoListFromDB()
                }

            }
            }
        }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}