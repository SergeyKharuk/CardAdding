package sergey.com.getwinner.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import sergey.com.getwinner.GetWinnerApp
import javax.inject.Inject

abstract class BaseActivity<P: Presenter> : AppCompatActivity(), BaseView {

    @Inject
    protected lateinit var presenter: P

    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showMessage(msg: String) {
        (applicationContext as GetWinnerApp).showToast(msg)
    }
}