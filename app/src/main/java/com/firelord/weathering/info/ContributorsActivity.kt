package com.firelord.weathering.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivityContributorsBinding

class ContributorsActivity : AppCompatActivity() {

    private lateinit var contributorsBinding: ActivityContributorsBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contributorsBinding = ActivityContributorsBinding.inflate(layoutInflater)
        val view = contributorsBinding.root
        setContentView(view)

        /*
        Create data list based on Data model
        textInfo = explain conti's job
        textName = conti's name
        textIcon = conti's photo
        */
        val dataList = ArrayList<ContributorsModel>()
        // Names are in A-Z order
        dataList.add(
            ContributorsModel(
                getString(R.string.str_devansh_info),
                getString(R.string.str_devansh),
                getDrawable(R.drawable.devansh)
            )
        )
        dataList.add(
            ContributorsModel(
                getString(R.string.str_harsh_info),
                getString(R.string.str_harsh),
                getDrawable(R.drawable.harsh)
            )
        )
        dataList.add(
            ContributorsModel(
                getString(R.string.str_sam_info),
                getString(R.string.str_sam),
                getDrawable(R.drawable.sam)
            )
        )
        dataList.add(
            ContributorsModel(
                getString(R.string.str_shubham_info),
                getString(R.string.str_shubham),
                getDrawable(R.drawable.shubham)
            )
        )

        val adapter = ContributorsRecyclerAdapter(this, dataList)
        recyclerView = contributorsBinding.rvContributors
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        contributorsBinding.fabConti.setOnClickListener {
            finish()
        }
    }
}