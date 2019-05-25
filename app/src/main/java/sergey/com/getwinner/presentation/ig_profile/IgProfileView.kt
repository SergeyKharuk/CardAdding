package sergey.com.getwinner.presentation.ig_profile

import sergey.com.getwinner.data.model.pojo.IgPost
import sergey.com.getwinner.data.model.response.SelfUserResponse
import sergey.com.getwinner.presentation.base.BaseView

interface IgProfileView : BaseView {

    fun loadSelfSuccess(selfUserResponse: SelfUserResponse)
    fun loadSelfError()
    fun loadPostsSuccess(igPosts: List<IgPost>)
    fun loadPostsError()
}