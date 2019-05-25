package sergey.com.getwinner.presentation.comments_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_comments_list.*
import sergey.com.getwinner.R
import sergey.com.getwinner.data.Keys
import sergey.com.getwinner.data.model.pojo.IgComment
import sergey.com.getwinner.presentation.base.BaseActivity
import sergey.com.getwinner.presentation.comments_list.adapter.CommentsAdapter

class CommentsListActivity : BaseActivity<CommentsListPresenter>(), CommentsListView {

    override val layoutResource: Int
        get() = R.layout.activity_comments_list

    //starter
    companion object {
        fun getLaunchIntent(context: Context, postId: String): Intent {
            val intent = Intent(context, CommentsListActivity::class.java)
            intent.putExtra(Keys.KEY_IG_POST_ID, postId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadComments(intent.getStringExtra(Keys.KEY_IG_POST_ID))
    }

    override fun loadCommentsSuccess(commentsList: List<IgComment>) {
        recycler_view_comments.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recycler_view_comments.adapter = CommentsAdapter(commentsList)
    }

    override fun loadCommentsError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}