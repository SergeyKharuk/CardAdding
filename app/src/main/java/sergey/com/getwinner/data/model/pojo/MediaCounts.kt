package sergey.com.getwinner.data.model.pojo

import com.google.gson.annotations.SerializedName

data class MediaCounts(
    @SerializedName("media") var postsCount: Int,
    @SerializedName("follows") var following: Int,
    @SerializedName("followed_by") var follows: Int
)