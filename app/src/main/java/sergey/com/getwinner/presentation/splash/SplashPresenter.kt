package sergey.com.getwinner.presentation.splash

import io.reactivex.observers.DisposableSingleObserver
import sergey.com.getwinner.data.model.request.TokenRequest
import sergey.com.getwinner.data.model.response.TokenResponse
import sergey.com.getwinner.data.repository.UserRepository
import sergey.com.getwinner.presentation.base.BasePresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val userRepository: UserRepository) : BasePresenter<SplashView>() {

    fun requestToken(code_: String) {
        unsubscribeOnDestroy(userRepository.requestToken(TokenRequest(code = code_)).subscribeWith(object : DisposableSingleObserver<TokenResponse>() {

            override fun onSuccess(tokenResponse: TokenResponse) {
                getView().showMessage(tokenResponse.accessToken)
            }

            override fun onError(e: Throwable) {
                getView().showMessage(e.toString())
            }

            override fun onStart() {
                getView().showMessage("start")
            }
        }))
    }

}