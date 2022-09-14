package com.wasusi.k_cloud.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.data.models.Folder

class FoldersAdapter(val folders: List<Folder>):
    RecyclerView.Adapter<FoldersAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = folders[position].name
        holder.created_at.text = folders[position].created_at
    }

    override fun getItemCount(): Int {
        return folders.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.title)
        val created_at = itemView.findViewById<TextView>(R.id.created)

    }


}