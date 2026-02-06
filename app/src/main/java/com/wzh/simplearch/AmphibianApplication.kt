package com.wzh.simplearch

import android.app.Application
import com.wzh.simplearch.data.AppContainer
import com.wzh.simplearch.data.DefaultAppContainer

/**
 * create by hao
 * 2026/2/6
 */
class AmphibianApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}