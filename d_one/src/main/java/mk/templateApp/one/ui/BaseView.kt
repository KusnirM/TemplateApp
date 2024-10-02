package mk.templateApp.one.ui

import mk.templateApp.presenter.base.BaseComposeFragment
import mk.templateApp.presenter.base.FeatureLoadingView

internal abstract class BaseView(screenName: String) :
    BaseComposeFragment(screenName),
    FeatureLoadingView by FeatureLoadingView.One

