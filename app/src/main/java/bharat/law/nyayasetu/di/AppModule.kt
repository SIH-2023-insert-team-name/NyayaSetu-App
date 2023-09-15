package bharat.law.nyayasetu.di

import android.content.Context
import bharat.law.nyayasetu.networking.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    private var mClient: OkHttpClient? = null

    val client: OkHttpClient
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)  /// show all JSON in logCat
                mClient = httpBuilder.build()

            }
            return mClient!!
        }

    @Provides
    fun getRetrofitService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
    @Provides
    fun getRetofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(bharat.law.nyayasetu.utils.Constants.Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

}