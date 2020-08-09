package com.firelord.weathering.info

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.R
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
            changelogActivity.prChangelog.visibility = View.VISIBLE
            withContext(IO) {
                githubApi.getChangelogAdapter().awaitResponse()
                    .run {
                        if (isSuccessful) {
                            body()?.let {
                                withContext(Dispatchers.Main) {
                                    changelogActivity.tvGitLog.text =
                                        it.logs.joinToString(separator = "\n")
                                    changelogActivity.prChangelog.visibility = View.GONE
                                }
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                changelogActivity.tvGitLog.text = getString(R.string.serverError)
                            }
                        }
                    }
            }
        }

        // using connectivityManager check for network state
        fun checkNetwork(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            val connected =
                capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
            return connected
        }

        if (activity?.let { checkNetwork(it) }!!) {
            lifecycleScope.launch {
                getGithubApi()
            }
        } else {
            // if user has no network report them with
            changelogActivity.tvGitLog.text = getString(R.string.noNet)
        }
    }
}