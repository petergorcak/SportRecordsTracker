package com.gorcak.sportperformancetracker.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gorcak.sportperformancetracker.record.data.datasource.database.RecordsDatabaseFactory
import com.gorcak.sportperformancetracker.record.data.datasource.database.RecordsDatabase
import com.gorcak.sportperformancetracker.record.data.datasource.remote.FirebaseRecordsDataSource
import com.gorcak.sportperformancetracker.record.data.datasource.remote.RemoteRecordsDataSource
import com.gorcak.sportperformancetracker.record.data.repository.DefaultRecordsRepository
import com.gorcak.sportperformancetracker.record.domain.RecordsRepository
import com.gorcak.sportperformancetracker.record.presentation.new_record.NewRecordViewModel
import com.gorcak.sportperformancetracker.record.presentation.records_list.RecordsListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedKoinModule = module {
    single {
        get<RecordsDatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<RecordsDatabase>().sportRecordsDao
    }
    singleOf(::FirebaseRecordsDataSource).bind<RemoteRecordsDataSource>()
    singleOf(::DefaultRecordsRepository).bind<RecordsRepository>()



    viewModelOf(::RecordsListViewModel)
    viewModelOf(::NewRecordViewModel)

}