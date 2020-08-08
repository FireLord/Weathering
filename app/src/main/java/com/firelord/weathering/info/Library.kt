package com.firelord.weathering.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.databinding.ActivityLibraryBinding
import com.mikepenz.aboutlibraries.Libs

class Library : AppCompatActivity() {

    private lateinit var libraryActivity: ActivityLibraryBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        libraryActivity = ActivityLibraryBinding.inflate(layoutInflater)
        val view = libraryActivity.root
        setContentView(view)

        val dataList = ArrayList<LibModel>()

        val libraries = Libs(this).libraries
        for (lib in libraries) {
            dataList.add(
                LibModel(
                    lib.libraryName,
                    lib.author,
                    lib.libraryVersion,
                    lib.libraryDescription,
                    lib.libraryWebsite
                )
            )
        }

        val adapter = LibRecyclerAdapter(this, dataList)
        recyclerView = libraryActivity.rvLibs
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        libraryActivity.fabLib.setOnClickListener {
            finish()
        }
    }
}