package com.firelord.weathering.presentation.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.databinding.FragmentLibraryBinding
import com.mikepenz.aboutlibraries.Libs

class LibraryFragment : Fragment() {

    private lateinit var libraryBinding: FragmentLibraryBinding
    private lateinit var recyclerView: RecyclerView

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

        val dataList = ArrayList<LibModel>()

        val libraries = Libs(requireContext()).libraries
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

        val adapter = LibRecyclerAdapter(requireActivity(), dataList)
        recyclerView = libraryBinding.rvLibs
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
    }
}