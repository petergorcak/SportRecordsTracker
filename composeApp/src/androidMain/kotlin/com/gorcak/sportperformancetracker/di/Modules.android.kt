package com.gorcak.sportperformancetracker.di

import com.gorcak.sportperformancetracker.record.data.datasource.database.RecordsDatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { RecordsDatabaseFactory(androidApplication()) }
    }