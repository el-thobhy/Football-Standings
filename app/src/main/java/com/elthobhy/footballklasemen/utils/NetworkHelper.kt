package com.elthobhy.footballklasemen.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkHelper(private val context: Context) {
    fun isNetworkConnected(): Boolean{
        val isNetworkConnected: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        isNetworkConnected = when{
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else->false
        }
        return isNetworkConnected
    }
}