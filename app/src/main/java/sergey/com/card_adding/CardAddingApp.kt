package sergey.com.getwinner

import android.app.Activity
import android.app.Application
import android.widget.Toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import sergey.com.getwinner.di.component.DaggerAppComponent
import sergey.com.getwinner.presentation.base.BaseActivity

import javax.inject.Inject

class GetWinnerApp : Application(), HasActivityInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>
    var toast: Toast? = null

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .context(this@GetWinnerApp)
            .build()
            .inject(this@GetWinnerApp)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    fun showToast(msg: String) {
        toast?.cancel()
        toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast!!.show()
    }
}