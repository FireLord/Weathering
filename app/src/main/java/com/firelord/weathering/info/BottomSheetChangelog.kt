package com.firelord.weathering.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.databinding.BottomSheetChangelogBinding
import com.firelord.weathering.info.data.GithubRawAPIService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class BottomSheetChangelog : BottomSheetDialogFragment() {

    private lateinit var changelogActivity: BottomSheetChangelogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changelogActivity = BottomSheetChangelogBinding.inflate(inflater)
        return changelogActivity.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val githubApi = GithubRawAPIService()
        suspend fun getGithubApi() {
            withContext(IO) {
                githubApi.getChangelogAdapter().awaitResponse()
                    .run {
                        if (isSuccessful) {
                            body()?.let {
                                withContext(Dispatchers.Main) {
                                    changelogActivity.tvGitLog.text = it.logs
                                }
                            }
                        }
                    }
            }
        }

        lifecycleScope.launch {
            getGithubApi()
        }
    }
}