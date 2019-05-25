package sergey.com.getwinner.presentation.comments_list

import io.reactivex.observers.DisposableSingleObserver
import sergey.com.getwinner.data.model.response.CommentsResponse
import sergey.com.getwinner.data.repository.UserRepository
import sergey.com.getwinner.presentation.base.BasePresenter
import javax.inject.Inject

class CommentsListPresenter @Inject constructor(private val  userRepository: UserRepository) : BasePresenter<CommentsListView>() {

    fun loadComments(postId: String) {
        unsubscribeOnDestroy(userRepository.getComments(postId).subscribeWith(object: DisposableSingleObserver<CommentsResponse>() {
            override fun onSuccess(commentsResponse: CommentsResponse) {
                commentsResponse.commentsList?.let { getView().loadCommentsSuccess(it) }
            }

            override fun onError(e: Throwable) {
                getView().showMessage(e.toString())
            }

            override fun onStart() {
                //todo: show progress
            }
        }))
    }

}