package com.wasusi.k_cloud.util.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.data.models.File
import com.wasusi.k_cloud.ui.folders.FolderDetailsActivity

class MonthsAdapter(val months: List<String>) :
    RecyclerView.Adapter<MonthsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_month_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.month.text =months[position]

        val filesList = ArrayList<File>()
        filesList.add(File("1", "Allan walker", "djsf", "mus","25th May 2022"))
        filesList.add(File("2", "CV", "djsf", "doc", "25th May 2022"))
        filesList.add(File("3", "Video1", "djsf", "img","25th May 2022"))
        filesList.add(File("4", "CV2", "djsf", "doc","25th May 2022"))
        holder.rvFiles.adapter = FilesAdapter(filesList)

        /**
         * Start folders detail activity onclick of folder
         * Pass folder name as an intent bundle
         */
        holder.itemView.setOnClickListener {
            it.context.startActivity(
                Intent(it.context, FolderDetailsActivity::class.java).apply {
                    putExtra("current_month", months[position] as java.io.Serializable)
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return months.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val month = itemView.findViewById<TextView>(R.id.title)
        val rvFiles = itemView.findViewById<RecyclerView>(R.id.rvSingleFiles)

    }


}