package mk.templateApp.commonDomain.service

import kotlinx.coroutines.flow.Flow

interface FeatureInstallManager {
    suspend fun installFeatureImmediately(featureName: String, downloadListener: DownloadListener?): Boolean
    suspend fun isFeatureInstalled(featureName: String): Boolean
    suspend fun installFeature(featureName: String, downloadListener: DownloadListener?): Flow<FState>
}

interface DownloadListener {
    fun onDownloadUpdate(totalBytes: Long, downloaded: Long)
}

sealed class FState {
    object Installed : FState()
    object Canceled : FState()
    object Failed : FState()
    object Pending : FState()
    object Downloading : FState()
    object Downloaded : FState()
    class Ignored : FState()
}

