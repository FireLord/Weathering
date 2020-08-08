package com.firelord.weathering.info

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R

class InfoRecyclerAdapter(context: Context, list: ArrayList<ContributorsModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val context: Context = context
    var list: ArrayList<ContributorsModel> = list

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ContiInfo: TextView = itemView.findViewById(R.id.tvContiInfo)
        var ContiName: TextView = itemView.findViewById(R.id.tvContiName)
        var ContiIcon: ImageView = itemView.findViewById(R.id.ivContiIcon)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            ContiInfo.text = recyclerViewModel.textInfo
            ContiName.text = recyclerViewModel.textName
            ContiIcon.setImageDrawable(recyclerViewModel.textIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return View1ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.contributors_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as View1ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}