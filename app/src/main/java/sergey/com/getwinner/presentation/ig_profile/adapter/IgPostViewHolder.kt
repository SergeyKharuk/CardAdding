package sergey.com.getwinner.presentation.ig_profile.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_ig_post.view.*
import sergey.com.getwinner.R
import sergey.com.getwinner.data.model.pojo.IgPost

class IgPostViewHolder(itemView: View, private val clickListener: OnIgPostClickListener) : RecyclerView.ViewHolder(itemView) {

    fun bindData(igPost: IgPost) {
        Glide.with(itemView.context)
            .load(igPost.igImages.lowResolution.url)
            .apply(RequestOptions().error(R.drawable.ic_error_placeholder).placeholder(R.drawable.ic_ig_post_placeholder))
            .into(itemView.image_view)
        itemView.image_view.setOnClickListener { clickListener.onPostClicked(igPost.id) }
    }

    interface OnIgPostClickListener {
        fun onPostClicked(postId: String)
    }

}