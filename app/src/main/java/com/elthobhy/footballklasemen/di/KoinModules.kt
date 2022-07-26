package com.elthobhy.footballklasemen.di

import androidx.room.Room
import com.elthobhy.footballklasemen.BuildConfig
import com.elthobhy.footballklasemen.data.StandingsRepository
import com.elthobhy.footballklasemen.data.local.LocalData
import com.elthobhy.footballklasemen.data.local.room.StandingsDatabase
import com.elthobhy.footballklasemen.data.remote.RemoteData
import com.elthobhy.footballklasemen.data.remote.network.ApiService
import com.elthobhy.footballklasemen.ui.detail.SeasonAdapter
import com.elthobhy.footballklasemen.ui.main.AllLeaguesAdapter
import com.elthobhy.footballklasemen.ui.standings.StandingAdapter
import com.elthobhy.footballklasemen.utils.AppExecutors
import com.elthobhy.footballklasemen.utils.NetworkHelper
import com.elthobhy.footballklasemen.viewmodel.allleagues.AllLeaguesViewModel
import com.elthobhy.footballklasemen.viewmodel.detailleague.DetailViewModel
import com.elthobhy.footballklasemen.viewmodel.seasonleague.SeasonViewModel
import com.elthobhy.footballklasemen.viewmodel.standingleague.StandingViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networking = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    single { Room.databaseBuilder(
        androidContext(), StandingsDatabase::class.java, "standings-db"
    ).fallbackToDestructiveMigration().build() }

    factory { get<StandingsDatabase>().standingsDao() }
}

val repositoryModule = module {
    single { RemoteData.getInstance() }
    single { LocalData.getInstance(get()) }
    single { StandingsRepository.getInstance(get(),get(),get(),get(), get()) }
    single { AppExecutors() }
    single { NetworkHelper(get()) }
}

val viewModelModule = module{
    single { AllLeaguesViewModel(get()) }
    single { DetailViewModel(get()) }
    single { SeasonViewModel(get()) }
    single { StandingViewModel(get(),get()) }
}

val adapterModule = module {
    factory { AllLeaguesAdapter() }
    factory { SeasonAdapter() }
    factory { StandingAdapter() }
}