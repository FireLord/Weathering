package com.firelord.weathering.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R

class SearchRecyclerAdapter(context: Context, list: ArrayList<SearchModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val context: Context = context
    var list: ArrayList<SearchModel> = list

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvTemp: TextView = itemView.findViewById(R.id.tvTempRow)
        var tvWeatherCondition: TextView = itemView.findViewById(R.id.tvWeatherTypeRow)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocationRow)
        var ivBg: ImageView = itemView.findViewById(R.id.ivBg)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvTemp.text = recyclerViewModel.textTemp
            tvWeatherCondition.text = recyclerViewModel.textCondition
            tvLocation.text = recyclerViewModel.textCity
            ivBg.setImageDrawable(recyclerViewModel.textImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return View1ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.locations_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as View1ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}