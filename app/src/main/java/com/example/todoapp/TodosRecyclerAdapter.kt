package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.database.model.Todo

class TodosRecyclerAdapter(var items:MutableList<Todo>?): RecyclerView.Adapter<TodosRecyclerAdapter.ViewHolder>() {




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView =itemView.findViewById(R.id.title)
        val describition: TextView =itemView.findViewById(R.id.describition)
        val markAsDone: ImageView =itemView.findViewById(R.id.mark_as_done)
        val delete : ImageView =itemView.findViewById(R.id.delete_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return ViewHolder(view)
    }

     fun changeData(newItems:MutableList<Todo>){
         items=newItems
         notifyDataSetChanged() //notify adapter that data changed


    }

    override fun getItemCount(): Int =items?.size?:0  //in case items is null return size  0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items!!.get(position)
        holder.title.setText(item.name)
        holder.describition.setText(item.details)


        holder.delete.setOnClickListener {
            onItemClickedToUpdated?.OnItemClickedToBeDeleted(position,item)
        }
    }


    var onItemClickedToUpdated:OnItemClicked?=null
    interface OnItemClicked{

        fun OnItemClickedToBeDeleted(position: Int,todo: Todo)
    }

}