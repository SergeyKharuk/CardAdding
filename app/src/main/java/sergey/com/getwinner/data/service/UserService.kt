package sergey.com.getwinner.data.service

import io.reactivex.Single
import retrofit2.http.*
import sergey.com.getwinner.data.model.request.TokenRequest
import sergey.com.getwinner.data.model.response.CommentsResponse
import sergey.com.getwinner.data.model.response.RecentMediaResponse
import sergey.com.getwinner.data.model.response.SelfUserResponse
import sergey.com.getwinner.data.model.response.TokenResponse

interface UserService {

    @FormUrlEncoded
    @POST("oauth/access_token")
    fun getToken(@Field("client_id") clientId: String,
                 @Field("client_secret") clientSecret: String,
                 @Field("grant_type") grantType: String,
                 @Field("redirect_uri") redirectUri: String,
                 @Field("code") code: String): Single<TokenResponse>

    @GET("v1/users/self")
    fun getSelf(@Query("access_token") token: String): Single<SelfUserResponse>

    @GET("v1/users/self/media/recent")
    fun getIgPosts(@Query("access_token") token: String,
                   @Query("count") perPage: Int): Single<RecentMediaResponse>

    @GET("v1/media/{id}/comments")
    fun getComments(@Path("id") postId: String,
                    @Query("access_token") token: String): Single<CommentsResponse>

}