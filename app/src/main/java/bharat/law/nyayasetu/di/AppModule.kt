package bharat.law.nyayasetu.di

import android.content.Context
import android.net.ConnectivityManager
import bharat.law.nyayasetu.networking.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    //    private var mClient: OkHttpClient? = null
//
//    val client: OkHttpClient
//        get() {
//            if (mClient == null) {
//                val interceptor = HttpLoggingInterceptor()
//                interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//                val httpBuilder = OkHttpClient.Builder()
//                httpBuilder
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(20, TimeUnit.SECONDS)
//                    .addInterceptor(ConnectVerifierInterceptor())  // Add the network availability check interceptor
//                    .addInterceptor(interceptor)  // Show all JSON in logCat
//                mClient = httpBuilder.build()
//            }
//            return mClient!!
//        }
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideConnectVerifierInterceptor(@ApplicationContext context: Context): Interceptor {
        return ConnectVerifierInterceptor(context)
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        connectVerifierInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(connectVerifierInterceptor)
            .build()
    }

    @Provides
    fun getRetrofitService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun getRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(bharat.law.nyayasetu.utils.Constants.Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

@ViewModelScoped
class ConnectVerifierInterceptor @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw IOException("No Network Available!")
        }
        val request = chain.request()
        return chain.proceed(request)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnected == true
    }
}