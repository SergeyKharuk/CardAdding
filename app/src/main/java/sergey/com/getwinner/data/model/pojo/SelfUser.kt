package sergey.com.getwinner.data.model.pojo

import com.google.gson.annotations.SerializedName

data class SelfUser(
    @SerializedName("id") var id: String?,
    @SerializedName("username") var userName: String?,
    @SerializedName("profile_picture") var userAvatar: String = "",
    @SerializedName("full_name") var fullName: String?,
    @SerializedName("bio") var bio: String?,
    @SerializedName("website") var webSite: String?,
    @SerializedName("is_business") var isBusiness: Boolean?,
    @SerializedName("counts") var mediaCounts: MediaCounts?
)