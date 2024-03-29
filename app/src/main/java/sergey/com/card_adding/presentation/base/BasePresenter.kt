package sergey.com.card_adding.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V: BaseView> : Presenter {

    private var mCompositeDisposables = CompositeDisposable()
    private var mView: WeakReference<V>? = null

    override fun attachView(view: Any) {
        mView = WeakReference<V>(view as V?)
    }

    override fun detachView() {
        mView?.clear()
        mView = null
    }

    override fun destroy() {
        mCompositeDisposables.clear()
    }

    override fun isViewAttached(): Boolean = mView != null

    protected fun getView(): V = mView!!.get()!!

    override fun unsubscribeOnDestroy(disposable: Disposable) {
        mCompositeDisposables.add(disposable)
    }
}