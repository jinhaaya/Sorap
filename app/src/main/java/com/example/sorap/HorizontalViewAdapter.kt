package com.example.sorap


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.temp_element.view.*

class HorizontalViewAdapter(var list: ArrayList<String>):  RecyclerView.Adapter<HorizontalViewAdapter.ListAdapter>(){

    class ListAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(LayoutInflater.from(parent.context).inflate(R.layout.temp_element, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.textListTitle.text = list[position]

        holder.layout.layoutListItem.setOnClickListener {
            Toast.makeText(holder.layout.context, "${list[position]} Click!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}