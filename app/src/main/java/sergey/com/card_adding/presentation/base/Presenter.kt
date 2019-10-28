package sergey.com.getwinner.presentation.base

import io.reactivex.disposables.Disposable

interface Presenter {

    fun attachView(view: Any)
    fun detachView()
    fun isViewAttached(): Boolean
    fun unsubscribeOnDestroy(disposable: Disposable)

}