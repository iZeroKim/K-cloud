package com.wasusi.k_cloud.util.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.data.models.Folder
import com.wasusi.k_cloud.ui.folders.FolderDetailsActivity

class FoldersAdapter(val folders: List<Folder>) :
    RecyclerView.Adapter<FoldersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = folders[position].name
        holder.created_at.text = folders[position].created_at


        /**
         * Start folders detail activity onclick of folder
         * Pass folder name as an intent bundle
         */
        holder.itemView.setOnClickListener {
            it.context.startActivity(
                Intent(it.context, FolderDetailsActivity::class.java).apply {
                    putExtra("current_folder", folders[position] as java.io.Serializable)
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return folders.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.title)
        val created_at = itemView.findViewById<TextView>(R.id.created)

    }


}