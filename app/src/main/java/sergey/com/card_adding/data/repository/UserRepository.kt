package sergey.com.getwinner.data.repository

import io.reactivex.Single
import sergey.com.getwinner.data.model.request.TokenRequest
import sergey.com.getwinner.data.model.response.CommentsResponse
import sergey.com.getwinner.data.model.response.RecentMediaResponse
import sergey.com.getwinner.data.model.response.SelfUserResponse
import sergey.com.getwinner.data.model.response.TokenResponse

interface UserRepository {

    fun getSelf(): Single<SelfUserResponse>
    fun requestToken(tokenRequest: TokenRequest): Single<TokenResponse>
    fun getPosts(): Single<RecentMediaResponse>
    fun getComments(postId: String): Single<CommentsResponse>

}