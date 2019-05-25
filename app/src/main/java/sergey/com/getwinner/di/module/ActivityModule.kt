package sergey.com.getwinner.di.module

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import sergey.com.getwinner.di.scopes.ActivityScope
import sergey.com.getwinner.presentation.comments_list.CommentsListActivity
import sergey.com.getwinner.presentation.ig_profile.IgProfileActivity
import sergey.com.getwinner.presentation.splash.SplashActivity

@Module(includes = [AndroidInjectionModule::class])
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun igProfileActivity(): IgProfileActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun commentsListActivity(): CommentsListActivity

}