package sergey.com.getwinner.presentation.ig_profile

import io.reactivex.observers.DisposableSingleObserver
import sergey.com.getwinner.data.model.response.RecentMediaResponse
import sergey.com.getwinner.data.model.response.SelfUserResponse
import sergey.com.getwinner.data.repository.UserRepository
import sergey.com.getwinner.presentation.base.BasePresenter
import javax.inject.Inject

class IgProfilePresenter @Inject constructor(private val  userRepository: UserRepository) : BasePresenter<IgProfileView>() {

    fun loadSelf() {
        unsubscribeOnDestroy(userRepository.getSelf().subscribeWith(object: DisposableSingleObserver<SelfUserResponse>() {
            override fun onSuccess(selfUserResponse: SelfUserResponse) {
                getView().loadSelfSuccess(selfUserResponse)
            }

            override fun onError(e: Throwable) {
                getView().showMessage(e.toString())
                getView().loadSelfError()
            }

            override fun onStart() {
                //todo: start progress
            }
        }))
    }

    fun loadPosts() {
        unsubscribeOnDestroy(userRepository.getPosts().subscribeWith(object: DisposableSingleObserver<RecentMediaResponse>() {
            override fun onSuccess(recentMediaResponse: RecentMediaResponse) {
                recentMediaResponse.data?.let { getView().loadPostsSuccess(it) }
            }

            override fun onError(e: Throwable) {
                getView().showMessage(e.toString())
                getView().loadPostsError()
            }

            override fun onStart() {
                //todo: start progress
            }
        }))
    }

}