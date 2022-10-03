package com.cesoft.cestocks.data

import androidx.room.Room
import com.cesoft.cestocks.data.database.AppDatabase
import com.cesoft.cestocks.data.repo.Repository
import com.cesoft.cestocks.domain.RepositoryContract
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "cestocks-database"
        ).build()
    }

    single<RepositoryContract> {
        Repository(get())
    }

}