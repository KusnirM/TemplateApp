package mk.templateApp.commonData.localstore

import mk.templateApp.commonDomain.repositories.ClearableCache
import javax.inject.Inject

interface ReferenceLocalStore {
    fun hasMaintenance(): Boolean
    fun cacheMaintenance(maintenance: String)
    fun getMaintenance(): String?
}

class ReferenceLocalStoreImpl @Inject constructor() : ReferenceLocalStore,
    ClearableCache {

    private var maintenance: String? = null

    override fun clearCache() {
        maintenance = null
    }

    override fun hasMaintenance(): Boolean = maintenance != null

    override fun cacheMaintenance(maintenance: String) {
        this.maintenance = maintenance
    }

    override fun getMaintenance(): String? = maintenance
}
