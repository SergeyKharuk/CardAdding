package sergey.com.getwinner.presentation.splash

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*
import sergey.com.getwinner.R
import sergey.com.getwinner.data.CLIENT_ID
import sergey.com.getwinner.data.REDIRECT_URI
import sergey.com.getwinner.data.shared_preference.IgAuthHelper
import sergey.com.getwinner.presentation.base.BaseActivity
import sergey.com.getwinner.presentation.ig_profile.IgProfileActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {

    @Inject lateinit var mIgAuthHelper: IgAuthHelper

    override val layoutResource: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            mIgAuthHelper.isLogged() -> openIgProfile()
            isIgLoginCodeEnable() -> requestToken()
            else -> loginToIg()
        }
    }

    override fun requestTokenSuccess(token: String) {
        showMessage("request token success: token = $token")
    }

    private fun openIgProfile() {
        val intent = Intent(this, IgProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    private fun loginToIg() {
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl("https://api.instagram.com/oauth/authorize/?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&response_type=code")
        finish()
    }

    private fun isIgLoginCodeEnable(): Boolean = !intent?.data?.getQueryParameter("code").isNullOrEmpty()

    private fun requestToken() {
        presenter.requestToken(intent!!.data!!.getQueryParameter("code")!!)
    }

}