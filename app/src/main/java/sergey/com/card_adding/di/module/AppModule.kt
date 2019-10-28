package sergey.com.card_adding.di.module

import dagger.Binds
import dagger.Module
import sergey.com.card_adding.data.repository.UserRepository
import sergey.com.card_adding.data.repository_impl.UserRepositoryImpl
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository

}

