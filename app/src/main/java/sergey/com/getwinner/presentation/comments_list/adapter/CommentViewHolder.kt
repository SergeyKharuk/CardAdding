package sergey.com.getwinner.presentation.comments_list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_comment.view.*
import sergey.com.getwinner.data.model.pojo.IgComment

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(comment: IgComment) {
        itemView.text_author.text = comment.from.userName
        itemView.text_comment_body.text = comment.text
    }

}