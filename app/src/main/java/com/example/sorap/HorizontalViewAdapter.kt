package com.example.sorap


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_grid_view.view.*
import kotlinx.android.synthetic.main.list_horizontal_view.view.*
import kotlinx.android.synthetic.main.list_horizontal_view.view.file_icon
import kotlinx.android.synthetic.main.list_horizontal_view.view.layoutListItem
import kotlinx.android.synthetic.main.list_horizontal_view.view.textListTitle

class HorizontalViewAdapter(var list: ArrayList<String>):  RecyclerView.Adapter<HorizontalViewAdapter.ListAdapter>(){

    class ListAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(LayoutInflater.from(parent.context).inflate(R.layout.list_horizontal_view, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.textListTitle.text = list[position]

        val filename_extension = list[position].substringAfterLast(".")

        when (filename_extension){
            "txt" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_txt)
            "pdf" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_pdf)
            "css" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_css)
            "jpg" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_jpg)
            "png" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_png)
            "html" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_html)
            "ppt", "pptx" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_ppt)
            "doc", "docx" -> holder.itemView.file_icon.setImageResource(R.drawable.ic_doc)
            else -> holder.itemView.file_icon.setImageResource(R.drawable.ic_etc)
        }

        holder.layout.layoutListItem.setOnClickListener {
            Toast.makeText(holder.layout.context, "${list[position]} Click!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}