package sergey.com.getwinner.data.model.response

import com.google.gson.annotations.SerializedName
import sergey.com.getwinner.data.model.pojo.Meta
import sergey.com.getwinner.data.model.pojo.SelfUser

data class SelfUserResponse(
    @SerializedName("data") var selfUser: SelfUser?,
    @SerializedName("meta") var meta: Meta
)