package sergey.com.getwinner.data.model.request

import com.google.gson.annotations.SerializedName
import sergey.com.getwinner.data.CLIENT_ID
import sergey.com.getwinner.data.CLIENT_SECRET
import sergey.com.getwinner.data.REDIRECT_URI

data class TokenRequest(
    @SerializedName("client_id") var clientId: String = CLIENT_ID,
    @SerializedName("client_secret") var clientSecret: String = CLIENT_SECRET,
    @SerializedName("grant_type") var grantType: String = "authorization_code",
    @SerializedName("redirect_uri") var redirectUri: String = REDIRECT_URI,
    @SerializedName("code") var code: String
)