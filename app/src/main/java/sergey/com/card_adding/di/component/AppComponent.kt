package sergey.com.card_adding.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import sergey.com.card_adding.CardAddingApp
import sergey.com.card_adding.di.module.ActivityModule
import sergey.com.card_adding.di.module.AppModule
import sergey.com.card_adding.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: CardAddingApp)
}