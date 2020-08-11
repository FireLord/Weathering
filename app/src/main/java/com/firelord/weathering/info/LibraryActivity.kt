package com.firelord.weathering.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.databinding.ActivityLibraryBinding
import com.mikepenz.aboutlibraries.Libs

class LibraryActivity : AppCompatActivity() {

    private lateinit var libraryBinding: ActivityLibraryBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        libraryBinding = ActivityLibraryBinding.inflate(layoutInflater)
        val view = libraryBinding.root
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
        recyclerView = libraryBinding.rvLibs
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        libraryBinding.fabLib.setOnClickListener {
            finish()
        }
    }
}