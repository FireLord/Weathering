package com.firelord.weathering.presentation.di

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.firelord.weathering.data.model.Library
import com.firelord.weathering.presentation.adapter.LibRecyclerAdapter
import com.mikepenz.aboutlibraries.Libs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideLibAdapter(
        @ApplicationContext context: Context,
    ):LibRecyclerAdapter{
        val dataList = ArrayList<Library>()

        val libraries = Libs(context).libraries
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
        return LibRecyclerAdapter(context,dataList)
    }
}