package com.firelord.weathering.presentation.ui.info

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R

class LibRecyclerAdapter(context: Context, list: ArrayList<LibModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    var list: ArrayList<LibModel> = list
    val libColor = context.resources.getStringArray(R.array.libColors)

    private inner class LibViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var libName: TextView = itemView.findViewById(R.id.tvLibName)
        var libAuthor: TextView = itemView.findViewById(R.id.tvAuthName)
        var libVersion: TextView = itemView.findViewById(R.id.tvLibVersion)
        var libInfo: TextView = itemView.findViewById(R.id.tvLibInfo)
        var cvLibs: CardView = itemView.findViewById(R.id.cvLibs)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            libName.text = recyclerViewModel.libName
            libAuthor.text = recyclerViewModel.libAuthor
            libVersion.text = recyclerViewModel.libVersion
            libInfo.text = recyclerViewModel.libInfo
            cvLibs.setOnClickListener {
                val uri: Uri =
                    Uri.parse(recyclerViewModel.libWebsite)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
            libName.setTextColor(Color.parseColor(libColor[position % libColor.size]))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LibViewHolder(
            LayoutInflater.from(context).inflate(R.layout.libs_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as LibViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}