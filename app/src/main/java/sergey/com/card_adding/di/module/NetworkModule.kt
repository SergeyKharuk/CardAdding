package sergey.com.getwinner.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sergey.com.getwinner.data.BASE_URL
import sergey.com.getwinner.data.READ_TIMEOUT
import sergey.com.getwinner.data.WRITE_TIMEOUT
import sergey.com.getwinner.data.factory.RxErrorHandlingCallAdapterFactory
import sergey.com.getwinner.data.service.UserService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

}