package sergey.com.getwinner.data.model.response

import com.google.gson.annotations.SerializedName
import sergey.com.getwinner.data.model.pojo.SelfUser

data class TokenResponse(
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("user") var user: SelfUser
)