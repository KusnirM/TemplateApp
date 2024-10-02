package mk.templateApp.home

import mk.templateApp.commonPresenter.base.BaseComposeFragment
import mk.templateApp.commonPresenter.base.FeatureLoadingView

abstract class BaseHomeView(screenName: String) :
    BaseComposeFragment(screenName),
    FeatureLoadingView by FeatureLoadingView.Home

