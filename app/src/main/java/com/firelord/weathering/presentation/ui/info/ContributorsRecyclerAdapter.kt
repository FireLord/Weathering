package com.firelord.weathering.presentation.ui.info

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R

class ContributorsRecyclerAdapter(context: Context, list: ArrayList<ContributorsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    var list: ArrayList<ContributorsModel> = list

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ContiInfo: TextView = itemView.findViewById(R.id.tvContiInfo)
        var ContiName: TextView = itemView.findViewById(R.id.tvContiName)
        var ContiIcon: ImageView = itemView.findViewById(R.id.ivContiIcon)
        val cvConti: CardView = itemView.findViewById(R.id.cvConti)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            ContiInfo.text = recyclerViewModel.textInfo
            ContiName.text = recyclerViewModel.textName
            ContiIcon.setImageDrawable(recyclerViewModel.textIcon)

            when (recyclerViewModel.textName) {
                context.getString(R.string.str_devansh) -> {
                    cvConti.setOnClickListener {
                        val uri: Uri =
                            Uri.parse("https://github.com/devanshbajaj")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                }
                context.getString(R.string.str_harsh) -> {
                    cvConti.setOnClickListener {
                        val uri: Uri =
                            Uri.parse("https://github.com/msfjarvis")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                }
                context.getString(R.string.str_sam) -> {
                    cvConti.setOnClickListener {
                        val uri: Uri =
                            Uri.parse("https://github.com/samirkushwaha")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                }
                else -> {
                    cvConti.setOnClickListener {
                        val uri: Uri =
                            Uri.parse("https://github.com/ishubhamsingh")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                }
            }
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