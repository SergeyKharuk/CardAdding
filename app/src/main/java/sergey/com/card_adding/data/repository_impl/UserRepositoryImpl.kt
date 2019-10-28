package sergey.com.getwinner.data.repository_impl

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sergey.com.getwinner.data.POSTS_PER_PAGE
import sergey.com.getwinner.data.model.request.TokenRequest
import sergey.com.getwinner.data.model.response.CommentsResponse
import sergey.com.getwinner.data.model.response.RecentMediaResponse
import sergey.com.getwinner.data.model.response.SelfUserResponse
import sergey.com.getwinner.data.model.response.TokenResponse
import sergey.com.getwinner.data.repository.UserRepository
import sergey.com.getwinner.data.service.UserService
import sergey.com.getwinner.data.shared_preference.IgAuthHelper
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService, private val igAuthHelper: IgAuthHelper) : UserRepository {

    override fun requestToken(tokenRequest: TokenRequest): Single<TokenResponse> {
        return userService.getToken(tokenRequest.clientId,
            tokenRequest.clientSecret,
            tokenRequest.grantType,
            tokenRequest.redirectUri,
            tokenRequest.code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { igAuthHelper.storeSessionToken(it.accessToken) }
    }

    override fun getSelf(): Single<SelfUserResponse> {
        return userService.getSelf(igAuthHelper.getSessionToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPosts(): Single<RecentMediaResponse> {
        return userService.getIgPosts(igAuthHelper.getSessionToken(), POSTS_PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getComments(postId: String): Single<CommentsResponse> {
        return userService.getComments(postId, igAuthHelper.getSessionToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}