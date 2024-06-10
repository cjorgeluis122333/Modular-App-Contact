package cu.xetid.dtvc.androidtrainingapp.network.di

import android.annotation.SuppressLint
import cu.xetid.dtvc.androidtrainingapp.common.APP_NAME
import cu.xetid.dtvc.androidtrainingapp.common.BuildConfig
import cu.xetid.dtvc.androidtrainingapp.common.CONSUMER_KEY
import cu.xetid.dtvc.androidtrainingapp.common.CONSUMER_SECRET
import cu.xetid.dtvc.androidtrainingapp.common.URL_WSO2_HOST
import cu.xetid.dtvc.androidtrainingapp.common.VERSION_NAME
import cu.xetid.dtvc.androidtrainingapp.common.security.CoreHttpClient
import cu.xetid.dtvc.androidtrainingapp.common.security.CoreRetrofit
import cu.xetid.dtvc.androidtrainingapp.common.util.base64Encode
import cu.xetid.dtvc.androidtrainingapp.domain.datasource.local.LocalAuthDatasource
import cu.xetid.dtvc.androidtrainingapp.network.service.InterceptService
import cu.xetid.dtvc.androidtrainingapp.network.service.TokenAuthenticator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Header
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val MAX_REQUEST_COUNT = 8
    private const val TIME_OUT_CONNECT = 25L
    private const val TIME_OUT_WRITE = 25L
    private const val TIME_OUT_READ = 25L

    @Singleton
    @Provides
    fun provideInterceptService(@CoreRetrofit retrofit: Retrofit): InterceptService =
        retrofit.create(InterceptService::class.java)

    @Singleton
    @Provides
    @CoreRetrofit
    fun provideRetrofit(
        @CoreHttpClient okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_WSO2_HOST)
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()

    @Singleton
    @Provides
    fun provideConverter(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    @CoreHttpClient
    fun provideOkHttpClient(
        hostnameVerifier: HostnameVerifier,
        dispatcher: Dispatcher,
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor,
        trustManagers: Array<X509TrustManager>,
        authenticator: Authenticator,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
        .hostnameVerifier(hostnameVerifier)
        .dispatcher(dispatcher)
        .addInterceptor(interceptor)
        .apply {
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            authenticator(authenticator)

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustManagers, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            sslSocketFactory(sslSocketFactory, trustManagers[0])
        }.build()


    @Singleton
    @Provides
    fun provideInterceptor(
        localAuthDatasource: LocalAuthDatasource,
        basicHeaders: Array<Header>
    ): Interceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().apply {
            basicHeaders.forEach { header ->
                header.toString().split(":").let {
                    addHeader(it[0].trim(), it[1].trim())
                }
            }
            val url = chain.request().url.toString()
            if (url.contains("/oauth2/token") || url.contains("/api/register") || url.contains("/oauth2/revoke")) {
                addHeader(
                    "Authorization",
                    "Basic ${base64Encode("${CONSUMER_KEY}:${CONSUMER_SECRET}".toByteArray())}",
                )
            } else {
                localAuthDatasource.getAccessToken()?.let {
                    addHeader("Authorization", "Bearer $it")
                    addHeader("hashed", base64Encode("Bearer $it"))
                }
            }
            val identityProviderAccess = runBlocking {
                localAuthDatasource.getIdentityProviderAccess().firstOrNull()?.toString()
                    ?: "TICKET"
            }
            addHeader("idp", identityProviderAccess.uppercase())
        }.build())
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideHostnameVerifier() = HostnameVerifier { _, _ -> true }

    @Singleton
    @Provides
    fun provideDispatcher() = Dispatcher().apply { maxRequests = MAX_REQUEST_COUNT }

    @Provides
    fun provideBasicHeaders(): Array<Header> = arrayOf(
        Header("User-Agent", "Android"),
        Header("App", APP_NAME),
        Header("App-Version", VERSION_NAME),
    )

    @Provides
    fun provideTrustManagers() =
        arrayOf<X509TrustManager>(@SuppressLint("CustomX509TrustManager") object :
            X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) = Unit
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

}


@Module
@InstallIn(SingletonComponent::class)
internal interface AuthenticatorModule {
    @Binds
    fun bindsAuthenticator(
        impl: TokenAuthenticator
    ): Authenticator
}