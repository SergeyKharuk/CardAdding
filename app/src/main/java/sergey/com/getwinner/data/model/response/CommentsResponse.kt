package sergey.com.getwinner.data.model.response

import com.google.gson.annotations.SerializedName
import sergey.com.getwinner.data.model.pojo.IgComment
import sergey.com.getwinner.data.model.pojo.Meta

data class CommentsResponse(
    @SerializedName("data") var commentsList: List<IgComment>?,
    @SerializedName("meta") var meta: Meta
)
