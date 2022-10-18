package com.umutcansahin.wallpaperapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umutcansahin.wallpaperapp.R
import com.umutcansahin.wallpaperapp.Util.Util
import com.umutcansahin.wallpaperapp.api.RetofitAPI
import com.umutcansahin.wallpaperapp.repo.ArtRepository
import com.umutcansahin.wallpaperapp.roomdb.ArtDao
import com.umutcansahin.wallpaperapp.roomdb.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Singleton
        @Provides
        fun injectRoomDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context,
            ArtDatabase::class.java,
            "database"
        ).build()


        @Singleton
        @Provides
        fun injectDao(database: ArtDatabase) = database.artDao()

        @Singleton
        @Provides
        fun injectRetrofitAPI(): RetofitAPI {
            return Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetofitAPI::class.java)
        }


        @Singleton
        @Provides
        fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background))

        @Singleton
        @Provides
        fun injectNormalRepo(dao: ArtDao, api: RetofitAPI) = ArtRepository(dao,api)

}