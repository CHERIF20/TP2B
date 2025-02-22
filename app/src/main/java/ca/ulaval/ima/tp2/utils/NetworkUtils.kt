package ca.ulaval.ima.tp2.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkUtils {
    @RequiresApi(Build.VERSION_CODES.M)
    fun getConnectionStatus(context: Context): String {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return "Aucune connexion"
        val capabilities = cm.getNetworkCapabilities(network) ?: return "Aucune connexion"
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "3G/LTE"
            else -> "Aucune connexion"
        }
    }
}