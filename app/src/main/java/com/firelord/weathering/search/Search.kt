package com.firelord.weathering.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.FragmentSearchBinding

class Search : Fragment() {

    private lateinit var searchActivity: FragmentSearchBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchActivity = FragmentSearchBinding.inflate(inflater)
        return searchActivity.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Add data on user's search
        val dataList = ArrayList<Data>()
        dataList.add(
            Data("Noida", "Snow","0",
                context?.getDrawable(R.drawable.snow)
            )
        )

        val adapter = activity?.let { SearchRecyclerAdapter(it, dataList) }
        recyclerView = searchActivity.rvSearch
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }
}