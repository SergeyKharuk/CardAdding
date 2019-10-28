package sergey.com.card_adding.presentation.base

import io.reactivex.disposables.Disposable

interface Presenter {

    fun attachView(view: Any)
    fun detachView()
    fun destroy()
    fun isViewAttached(): Boolean
    fun unsubscribeOnDestroy(disposable: Disposable)

}