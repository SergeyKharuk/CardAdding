package sergey.com.card_adding.di.module

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import sergey.com.card_adding.di.scopes.ActivityScope
import sergey.com.card_adding.presentation.add_card.AddCardActivity

@Module(includes = [AndroidInjectionModule::class])
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun addCardActivity(): AddCardActivity

}