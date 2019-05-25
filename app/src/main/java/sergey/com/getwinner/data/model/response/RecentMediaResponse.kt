package sergey.com.getwinner.data.model.response

import com.google.gson.annotations.SerializedName
import sergey.com.getwinner.data.model.pojo.IgPost
import sergey.com.getwinner.data.model.pojo.Meta

data class RecentMediaResponse(
    @SerializedName("pagination") var pagination: Pagination?,
    @SerializedName("data") var data: List<IgPost>?,
    @SerializedName("meta") var meta: Meta
)

data class Pagination(
    @SerializedName("next_max_id") var nextMaxId: String,
    @SerializedName("next_url") var nextUrl: String
)