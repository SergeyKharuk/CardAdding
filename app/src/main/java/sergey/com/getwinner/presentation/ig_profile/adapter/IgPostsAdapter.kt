package sergey.com.getwinner.presentation.ig_profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sergey.com.getwinner.R
import sergey.com.getwinner.data.model.pojo.IgPost

class IgPostsAdapter(private val items: List<IgPost>,
                     private  val clickListener: IgPostViewHolder.OnIgPostClickListener)
    : RecyclerView.Adapter<IgPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IgPostViewHolder =
        IgPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ig_post, parent, false), clickListener)

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: IgPostViewHolder, position: Int)  = holder.bindData(items[position])
}