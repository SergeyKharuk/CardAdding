package sergey.com.getwinner.data.model.pojo

import com.google.gson.annotations.SerializedName

data class IgPost(
    @SerializedName("id") var id: String,
    @SerializedName("user") var user: SelfUser,
    @SerializedName("images") var igImages: IgImages,
    @SerializedName("created_time") var createdTime: Long,
    @SerializedName("caption") var caption: Caption,
    @SerializedName("user_has_liked") var isLiked: Boolean,
    @SerializedName("likes") var likesCount: Likes,
    @SerializedName("tags") var tags: List<String>,
    @SerializedName("filter") var filter: String,
    @SerializedName("comments") var comments: Comments,
    @SerializedName("type") var type: String,
    @SerializedName("link") var link: String,
    @SerializedName("location") var location: Location,
    @SerializedName("attribution") var attribution: Any?,
    @SerializedName("users_in_photo") var usersInPhoto: Any?
)

data class IgImages(
    @SerializedName("thumbnail") var thubmnail: IgImage,
    @SerializedName("low_resolution") var lowResolution: IgImage,
    @SerializedName("standard_resolution") var standardResolution: IgImage
)

data class IgImage(
    @SerializedName("width") var width: Int,
    @SerializedName("height") var height: Int,
    @SerializedName("url") var url: String
)

data class Caption(
    @SerializedName("id") var id: String,
    @SerializedName("text") var text: String,
    @SerializedName("created_time") var createdTime: Long,
    @SerializedName("from") var from: SelfUser
)

data class Likes(
    @SerializedName("count") var count: Int
)

data class Comments(
    @SerializedName("count") var count: Int
)

data class Location(
    @SerializedName("latitude") var latitude: Float,
    @SerializedName("longitude") var longitude: Float,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: Long
)