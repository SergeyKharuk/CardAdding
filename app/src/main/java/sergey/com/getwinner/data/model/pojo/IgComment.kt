package sergey.com.getwinner.data.model.pojo

import com.google.gson.annotations.SerializedName

data class IgComment(
    @SerializedName("id") var id :String,
    @SerializedName("from") var from: SelfUser,
    @SerializedName("text") var text: String,
    @SerializedName("created_time") var createdTime: Long
)