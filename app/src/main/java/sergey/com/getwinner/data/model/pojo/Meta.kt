package sergey.com.getwinner.data.model.pojo

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code") var code: Int,
    @SerializedName("error_type") var errorType: String?,
    @SerializedName("error_message") var errorMessage: String?
)