package sergey.com.getwinner.presentation.splash

import sergey.com.getwinner.presentation.base.BaseView

interface SplashView : BaseView {

    fun requestTokenSuccess(token: String)

}