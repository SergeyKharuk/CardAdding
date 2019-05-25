package sergey.com.getwinner.di.module

import dagger.Binds
import dagger.Module
import sergey.com.getwinner.data.repository.UserRepository
import sergey.com.getwinner.data.repository_impl.UserRepositoryImpl
import sergey.com.getwinner.data.shared_preference.IgAuthHelper
import sergey.com.getwinner.data.shared_preference.IgAuthHelperImpl
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun provideIgAuthHelper(igAuthHelper: IgAuthHelperImpl): IgAuthHelper

}

