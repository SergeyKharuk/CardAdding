package sergey.com.card_adding.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import sergey.com.card_adding.CardAddingApp

abstract class BaseActivity<P: Presenter> : AppCompatActivity(), BaseView {

    protected lateinit var presenter: P

    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        if (isFinishing) presenter.destroy()
    }

    override fun showMessage(msg: String) {
        (applicationContext as CardAddingApp).showToast(msg)
    }
}