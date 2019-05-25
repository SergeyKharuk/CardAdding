package sergey.com.getwinner.presentation.ig_profile

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_ig_profile.*
import sergey.com.getwinner.R
import sergey.com.getwinner.data.model.pojo.IgPost
import sergey.com.getwinner.data.model.response.RecentMediaResponse
import sergey.com.getwinner.data.model.response.SelfUserResponse
import sergey.com.getwinner.presentation.base.BaseActivity
import sergey.com.getwinner.presentation.comments_list.CommentsListActivity
import sergey.com.getwinner.presentation.ig_profile.adapter.IgPostViewHolder
import sergey.com.getwinner.presentation.ig_profile.adapter.IgPostsAdapter

class IgProfileActivity : BaseActivity<IgProfilePresenter>(),
    IgProfileView,
    IgPostViewHolder.OnIgPostClickListener {

    override val layoutResource: Int
        get() = R.layout.activity_ig_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadSelf()
        presenter.loadPosts()
    }

    override fun loadSelfSuccess(selfUserResponse: SelfUserResponse) {
        // user avatar
        Glide.with(this)
            .load(selfUserResponse.selfUser?.userAvatar)
            .apply(RequestOptions().circleCrop())
            .into(image_user_avatar)

        //followers count
        text_followers_count.text = selfUserResponse.selfUser?.mediaCounts?.follows.toString()

        // following count
        text_following_count.text = selfUserResponse.selfUser?.mediaCounts?.following.toString()

        //user name
        text_username.text = selfUserResponse.selfUser?.fullName

        //bio
        text_bio.text = selfUserResponse.selfUser?.bio
    }

    override fun loadSelfError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadPostsSuccess(igPosts: List<IgPost>) {
        val glm  = GridLayoutManager(this, 3)
        recycler_view.layoutManager = glm
        val adapter = IgPostsAdapter(igPosts, this)
        recycler_view.adapter = adapter
    }

    override fun loadPostsError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostClicked(postId: String) = startActivity(CommentsListActivity.getLaunchIntent(this, postId))


}