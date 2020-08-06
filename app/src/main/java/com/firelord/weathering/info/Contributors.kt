package com.firelord.weathering.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivityContributorsBinding

class Contributors : AppCompatActivity() {

    private lateinit var contributorsActivity: ActivityContributorsBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contributorsActivity = ActivityContributorsBinding.inflate(layoutInflater)
        val view = contributorsActivity.root
        setContentView(view)

        /*
        Create data list based on Data model
        textInfo = explain conti's job
        textName = conti's name
        textIcon = conti's photo
        */
        val dataList = ArrayList<Data>()
        // Names are in A-Z order
        dataList.add(
            Data(getString(R.string.devanshInfo), getString(R.string.devansh), getDrawable(R.drawable.devansh))
        )
        dataList.add(
            Data(getString(R.string.harshInfo), getString(R.string.harsh), getDrawable(R.drawable.harsh))
        )
        dataList.add(
            Data(getString(R.string.samInfo), getString(R.string.sam), getDrawable(R.drawable.sam))
        )
        dataList.add(
            Data(getString(R.string.shubhamInfo), getString(R.string.shubham), getDrawable(R.drawable.shubham))
        )

        val adapter = InfoRecyclerAdapter(this, dataList)
        recyclerView = contributorsActivity.rvContributors
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}