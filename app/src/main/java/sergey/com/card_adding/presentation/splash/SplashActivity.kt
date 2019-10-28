package sergey.com.card_adding.presentation.splash

import android.os.Bundle
import android.os.Handler
import sergey.com.card_adding.R
import sergey.com.card_adding.presentation.add_card.AddCardActivity
import sergey.com.card_adding.presentation.base.BaseActivity

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {

    override val layoutResource: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({startActivity(AddCardActivity.getLaunchIntent(this@SplashActivity))}, 500)
    }
}