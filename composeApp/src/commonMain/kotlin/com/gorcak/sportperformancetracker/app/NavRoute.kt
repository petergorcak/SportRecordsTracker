package com.gorcak.sportperformancetracker.app

import kotlinx.serialization.Serializable

sealed interface NavRoute {

    @Serializable
    data object RootGraph: NavRoute

    @Serializable
    data object RecordsList: NavRoute

    @Serializable
    data object CreateRecord: NavRoute
}