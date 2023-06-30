package com.firelord.weathering.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(inflater)
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Add data on user's search
        val dataList = ArrayList<SearchModel>()
        dataList.add(
            SearchModel(
                "Noida", "Snow", "0",
                context?.getDrawable(R.drawable.snow)
            )
        )

        val adapter = activity?.let { SearchRecyclerAdapter(it, dataList) }
        recyclerView = searchBinding.rvSearch
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }
}