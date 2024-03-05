package com.firelord.weathering.presentation.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.R
import com.firelord.weathering.data.util.NetworkCheck.checkNetwork
import com.firelord.weathering.databinding.BottomSheetChangelogBinding
import com.firelord.weathering.presentation.ui.info.data.GithubRawAPIService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class BottomSheetChangelog : BottomSheetDialogFragment() {

    private lateinit var changelogBinding: BottomSheetChangelogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changelogBinding = BottomSheetChangelogBinding.inflate(inflater)
        return changelogBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val githubApi = GithubRawAPIService()
        suspend fun getGithubApi() {
            changelogBinding.prChangelog.visibility = View.VISIBLE
            withContext(IO) {
                githubApi.getChangelogAdapter().awaitResponse()
                    .run {
                        if (isSuccessful) {
                            body()?.let {
                                withContext(Dispatchers.Main) {
                                    changelogBinding.tvGitLog.text =
                                        it.logs.joinToString(separator = "\n")
                                    changelogBinding.prChangelog.visibility = View.GONE
                                }
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                changelogBinding.tvGitLog.text = getString(R.string.str_server_error)
                            }
                        }
                    }
            }
        }

        if (activity?.let { checkNetwork(it) }!!) {
            lifecycleScope.launch {
                getGithubApi()
            }
        } else {
            // if user has no network report them with
            changelogBinding.tvGitLog.text = getString(R.string.str_no_net)
        }
    }
}