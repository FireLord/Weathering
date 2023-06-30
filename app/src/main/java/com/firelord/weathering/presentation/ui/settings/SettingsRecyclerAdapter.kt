package com.firelord.weathering.presentation.ui.settings

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R

class SettingsRecyclerAdapter(context: Context, list: ArrayList<SettingsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1 // simple_setting_row
        const val VIEW_TYPE_TWO = 2 // setting_unit_row
    }

    private val context: Context = context
    var list: ArrayList<SettingsModel> = list

    // set data to viewHolder 1
    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var optionInfo: TextView = itemView.findViewById(R.id.tvOptionInfo)
        var optionName: TextView = itemView.findViewById(R.id.tvOptionName)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            optionInfo.text = recyclerViewModel.textInfo
            optionName.text = recyclerViewModel.textName
            optionName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                recyclerViewModel.textIcon,
                0,
                0,
                0
            )
            // Open dark mode bottom sheet
            if (recyclerViewModel.textName == context.getString(R.string.str_dark_mode)) {
                optionInfo.setOnClickListener {
                    val bottomSheet = BottomSheetDarkMode()
                    bottomSheet.show(
                        (context as FragmentActivity).supportFragmentManager,
                        bottomSheet.tag
                    )
                }
            }
            // Open location activity
            if (recyclerViewModel.textName == context.getString(R.string.str_location)) {
                optionInfo.setOnClickListener {
                    val intent = Intent(context, LocationActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

    // set data to viewHolder 2
    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var optionInfo: TextView = itemView.findViewById(R.id.tvOptionInfo)
        var optionName: TextView = itemView.findViewById(R.id.tvOptionName)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            optionInfo.text = recyclerViewModel.textInfo
            optionName.text = recyclerViewModel.textName
            optionName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                recyclerViewModel.textIcon,
                0,
                0,
                0
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.simple_setting_row, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.setting_unit_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

}
