package mk.templateApp.one.domain.repositories

import mk.templateApp.one.domain.models.SettingsModel

interface HomeRepository {

    suspend fun getSettings(): SettingsModel

}
