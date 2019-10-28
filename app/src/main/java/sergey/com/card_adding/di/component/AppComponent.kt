package sergey.com.getwinner.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import sergey.com.getwinner.GetWinnerApp
import sergey.com.getwinner.di.module.ActivityModule
import sergey.com.getwinner.di.module.AppModule
import sergey.com.getwinner.di.module.NetworkModule
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

    fun inject(app: GetWinnerApp)
}