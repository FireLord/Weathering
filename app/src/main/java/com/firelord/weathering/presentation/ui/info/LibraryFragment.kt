package com.firelord.weathering.presentation.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.firelord.weathering.data.model.Library
import com.firelord.weathering.databinding.FragmentLibraryBinding
import com.firelord.weathering.presentation.adapter.LibRecyclerAdapter
import com.mikepenz.aboutlibraries.Libs

class LibraryFragment : Fragment() {

    private lateinit var libraryBinding: FragmentLibraryBinding
    private lateinit var libRecyclerAdapter: LibRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        libraryBinding = FragmentLibraryBinding.inflate(inflater)
        return libraryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = ArrayList<Library>()

        val libraries = Libs(requireContext()).libraries
        for (lib in libraries) {
            dataList.add(
                Library(
                    lib.libraryName,
                    lib.author,
                    lib.libraryVersion,
                    lib.libraryDescription,
                    lib.libraryWebsite
                )
            )
        }
        initRecyclerView(dataList)
    }

    private fun initRecyclerView(dataList: ArrayList<Library>){
        libRecyclerAdapter = LibRecyclerAdapter(requireActivity(), dataList)
        libraryBinding.rvLibs.adapter = libRecyclerAdapter
        libraryBinding.rvLibs.layoutManager = LinearLayoutManager(activity)
    }
}