package com.example.popcorntime.old_needs_sorting.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionUtil(context: Context) {
    private var connectivityManager: ConnectivityManager? = context.getSystemService(ConnectivityManager::class.java)

    fun isConnected(): Boolean {
        val activeNetwork =
            connectivityManager?.getNetworkCapabilities(connectivityManager!!.activeNetwork)
                ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}