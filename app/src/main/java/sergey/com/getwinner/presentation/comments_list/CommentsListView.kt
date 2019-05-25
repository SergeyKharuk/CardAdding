package sergey.com.getwinner.presentation.comments_list

import sergey.com.getwinner.data.model.pojo.IgComment
import sergey.com.getwinner.presentation.base.BaseView

interface CommentsListView : BaseView {

    fun loadCommentsSuccess(commentsList: List<IgComment>)
    fun loadCommentsError()

}