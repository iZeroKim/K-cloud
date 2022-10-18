package com.wasusi.k_cloud.util

import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.ui.folders.FolderDetailsActivity

//homeViewModel: HomeViewModel,
fun showFileAddDialog( activity: FolderDetailsActivity) {
    val dialog = BottomSheetDialog(activity)

    val btmSheetView = activity.layoutInflater.inflate(R.layout.add_file_layout, null, false)
    dialog.setContentView(btmSheetView)
    dialog.setCancelable(true)

    val btnCancel = btmSheetView.findViewById<ImageView>(R.id.btnCancel)
    btnCancel.setOnClickListener {
        dialog.dismiss()
    }

//    val btn_add_folder = btmSheetView.findViewById<Button>(R.id.btnAddFolder)
//    btn_add_folder.setOnClickListener {
//        val name = btmSheetView.findViewById<EditText>(R.id.etName).text.toString()
//
//        /**
//         * Save folder
//         * */
//        var calendar: Calendar? = Calendar.getInstance()
//        val date: Date = calendar!!.getTime()
//        val currentDate = "${SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime())}, ${date}"
//
//        homeViewModel.insertFolder(name , currentDate)
//        dialog.dismiss()
//    }

    dialog.show()

}