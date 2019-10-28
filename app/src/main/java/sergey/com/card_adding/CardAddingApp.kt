package sergey.com.card_adding

import android.app.Activity
import android.app.Application
import android.widget.Toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import sergey.com.card_adding.di.component.DaggerAppComponent

import javax.inject.Inject

class CardAddingApp : Application(), HasActivityInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>
    var toast: Toast? = null

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .context(this@CardAddingApp)
            .build()
            .inject(this@CardAddingApp)
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