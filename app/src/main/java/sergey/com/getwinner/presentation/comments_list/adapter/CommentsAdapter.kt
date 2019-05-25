package sergey.com.getwinner.presentation.comments_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sergey.com.getwinner.R
import sergey.com.getwinner.data.model.pojo.IgComment

class CommentsAdapter(private val commentsList: List<IgComment>) : RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))

    override fun getItemCount(): Int = commentsList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) = holder.bindData(commentsList[position])
}