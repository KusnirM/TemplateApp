package mk.templateApp.two.ui

import mk.templateApp.presenter.base.BaseComposeFragment
import mk.templateApp.presenter.base.FeatureLoadingView

internal abstract class BaseTwoView(screenName: String) :
    BaseComposeFragment(screenName),
    FeatureLoadingView by FeatureLoadingView.Two
