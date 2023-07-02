package com.firelord.weathering.presentation.adapter

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
import com.firelord.weathering.databinding.LibsRowBinding
import com.firelord.weathering.data.model.Library
import javax.inject.Inject

class LibRecyclerAdapter
@Inject
    constructor(
    private val context: Context,
    private val list: ArrayList<Library>
    ): RecyclerView.Adapter<LibRecyclerAdapter.LibViewHolder>() {

    val libColor: Array<String> = context.resources.getStringArray(R.array.libColors)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibViewHolder {
        val binding = LibsRowBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return LibViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibViewHolder, position: Int) {
        (holder as LibViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class LibViewHolder(
        val binding:LibsRowBinding
        ):RecyclerView.ViewHolder(binding.root){
            fun bind(position: Int){
                val recyclerViewModel = list[position]
                binding.tvLibName.text = recyclerViewModel.libName
                binding.tvAuthName.text = recyclerViewModel.libAuthor
                binding.tvLibVersion.text = recyclerViewModel.libVersion
                binding.tvLibInfo.text = recyclerViewModel.libInfo
                binding.cvLibs.setOnClickListener {
                    val uri: Uri =
                        Uri.parse(recyclerViewModel.libWebsite)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }
                binding.tvLibName.setTextColor(Color.parseColor(libColor[position % libColor.size]))
            }
        }

}