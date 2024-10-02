package mk.templateApp.home.data.di

import dagger.Binds
import mk.templateApp.home.data.net.AClient
import mk.templateApp.home.data.net.AClientImpl
import dagger.Module
import mk.templateApp.home.data.repository.PremiumRepositoryImpl
import mk.templateApp.home.domain.repositories.PremiumRepository
import mk.templateApp.commonData.di.scopes.ModuleScope

//@Module
abstract class ADataModule {
//
//    @ModuleScope
//    @Provides
//    internal fun provideAClient(
//        AClientImpl: AClientImpl,
//    ): AClient = AClientImpl

//    @ModuleScope
//    @Binds
//    internal abstract fun provideChatRepository(
//        impl: PremiumRepositoryImpl,
//    ): PremiumRepository
//
//    @ModuleScope
//    @Binds
//    internal abstract fun provideChatClient(impl: AClientImpl): AClient
}
